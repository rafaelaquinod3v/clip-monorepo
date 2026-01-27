package sv.com.clip.dictionary.application.services

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import sv.com.clip.dictionary.application.LexicalBatchPersister
import sv.com.clip.dictionary.domain.model.Form
import sv.com.clip.dictionary.domain.model.FormId
import sv.com.clip.dictionary.domain.model.LexicalEntry
import sv.com.clip.dictionary.domain.model.LexicalEntryId
import sv.com.clip.dictionary.domain.model.LexiconId
import sv.com.clip.dictionary.domain.model.Sense
import sv.com.clip.dictionary.domain.model.SenseId
import sv.com.clip.dictionary.domain.model.UsageExample
import sv.com.clip.dictionary.domain.queries.LexiconProvider
import sv.com.clip.dictionary.domain.valueObjects.Language
import sv.com.clip.dictionary.domain.valueObjects.PartOfSpeech
import java.io.File
import kotlin.collections.map

@Service
class ImportVocabularyService(
  private val lexiconProvider: LexiconProvider,
  private val messagingTemplate: SimpMessagingTemplate,
  private val objectMapper: ObjectMapper,
  private val lexicalBatchPersister: LexicalBatchPersister,
) {
  // Definimos el Scope aquí para desacoplarlo del ciclo de vida del Controller
  private val appScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

  fun startAsyncImport(file: File, lang: Language) {
    appScope.launch {
      try {
        val total = countElements(file)
        processWithProgress(file, total, lang)
      } catch (e: Exception) {
        sendUpdate("error", 0, 0, e.message)
      } finally {
        file.delete() // Limpieza de archivos temporales
      }
    }
  }

  private suspend fun countElements(file: File): Int = withContext(Dispatchers.IO) {
    val parser = JsonFactory().createParser(file)
    var count = 0
    parser.use {
      if (it.nextToken() == JsonToken.START_ARRAY) {
        while (it.nextToken() != null) {
          if (it.currentToken == JsonToken.START_OBJECT) {
            count++
            it.skipChildren() // Salta el contenido del objeto para ir rápido
          }
        }
      }
    }
    count
  }

  private suspend fun processWithProgress(file: File, total: Int, lang: Language) {
    val parser = JsonFactory().createParser(file)
    var current = 0
    val batchSize = 100
    val buffer = mutableListOf<LexicalEntry>()
    val lexiconId = lexiconProvider.findByLang(lang)!!.id
    parser.use {
      if (it.nextToken() == JsonToken.START_ARRAY) {
        while (it.nextToken() == JsonToken.START_OBJECT) {
          // Mapeo selectivo del objeto actual
          val item = objectMapper.readTree<com.fasterxml.jackson.databind.JsonNode>(it)
          buffer.add(mapJsonToDomain(item, lexiconId))
          if(buffer.size >= batchSize) {
            lexicalBatchPersister.saveBatch(buffer.toList())
            buffer.clear()
            yield()
          }

          current++

          // Cálculo y envío de progreso
          val percent = (current.toDouble() / total * 100).toInt()
          if (current % 100 == 0 || current == total) { // Throttling
            sendUpdate("processing", current, total, percent = percent)
          }
        }
        if(buffer.isNotEmpty()) {
          lexicalBatchPersister.saveBatch(buffer.toList())
          buffer.clear()
        }
      }
    }
    sendUpdate("completed", current, total, percent = 100)
  }

  private fun sendUpdate(status: String, current: Int, total: Int, msg: String? = null, percent: Int = 0) {
    messagingTemplate.convertAndSend("/import-omw" as String, mapOf(
      "status" to status,
      "current" to current,
      "total" to total,
      "percentage" to percent,
      "message" to msg
    ) as Any)
  }

  private fun mapJsonToDomain(node: JsonNode, lexiconId: LexiconId) : LexicalEntry {
    val forms = node.get("forms").map { formNode ->
      Form(
        id = FormId.generate(),
        writtenRepresentation = formNode.get("writtenRepresentation").asText(),
      )
    }
    val senses = node.get("senses").map { senseNode ->
      Sense(
        id = SenseId.generate(),
        sourceId = senseNode.get("source_id").asText(),
        definition = senseNode.get("definition")?.asText(),
        conceptIli = senseNode.get("ili").asText(),
        examples = senseNode.get("usageExamples").map { exNode ->
          UsageExample(text = exNode.asText())
        }
      )
    }
    return LexicalEntry(
      id = LexicalEntryId.generate(),
      sourceId = node.get("source_id").asText(),
      lemma = node.get("lemma").asText(),
      lexiconId = lexiconId,
      partOfSpeech = PartOfSpeech.valueOf(node.get("pos").asText()),
      forms = forms,
      senses = senses
    )
  }
}
