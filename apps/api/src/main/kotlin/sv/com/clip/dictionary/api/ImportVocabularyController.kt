package sv.com.clip.dictionary.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import sv.com.clip.dictionary.infrastructure.ImportVocabularyService
//import tools.jackson.databind.ObjectMapper
import java.io.File
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.*
//import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vocabulary")
class ImportVocabularyController(
  private val service: ImportVocabularyService,
  private val messagingTemplate: SimpMessagingTemplate,
  private val objectMapper: ObjectMapper
  ) {
  private val uploadScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

  @GetMapping("/import")
  fun importVocabulary(): Boolean {
    service.importVocabulary()
    return true
  }

  @GetMapping("/embedding")
  fun embeddingVocabulary(): Boolean {
    service.vocabularyAddEmbeddings()
    return true
  }

  @PostMapping("/import-omw-json") // Open Multilingual WordNet
  fun importVocabularyOmwJson(@RequestParam("file") file: MultipartFile): Boolean {
    try {
      val fileName = file.originalFilename ?: "unknown_${System.currentTimeMillis()}"
      val destFile = File("/var/opt/clip/uploads/$fileName")
      //val fileName = file.originalFilename
      file.transferTo(destFile)
      uploadScope.launch {
        try {
          // 1. Pre-lectura: Contar elementos totales (rápido y bajo consumo de RAM)
          val totalItems = countElements(destFile)

          // 2. Procesamiento real con progreso
          processWithProgress(destFile, totalItems)
        }catch (e: Exception) {
          println("Error: ${e.message}")
          sendUpdate("error", 0, 0, e.message)
        }
      }
      return true
    }catch (e: Exception) {
      return false
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

  private suspend fun processWithProgress(file: File, total: Int) {
    val parser = JsonFactory().createParser(file)
    var current = 0

    parser.use {
      if (it.nextToken() == JsonToken.START_ARRAY) {
        while (it.nextToken() == JsonToken.START_OBJECT) {
          // Mapeo selectivo del objeto actual
          val item = objectMapper.readTree<com.fasterxml.jackson.databind.JsonNode>(it)

          // Simulación de guardado y espera
          delay(100)
          current++

          // Cálculo y envío de progreso
          val percent = (current.toDouble() / total * 100).toInt()
          sendUpdate("processing", current, total, percent = percent)
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

}
