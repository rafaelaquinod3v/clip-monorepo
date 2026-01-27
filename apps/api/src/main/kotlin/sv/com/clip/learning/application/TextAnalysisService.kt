package sv.com.clip.learning.application

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sv.com.clip.dictionary.api.DictionaryExternal
import sv.com.clip.learning.domain.WordStatus
import sv.com.clip.learning.domain.events.WordsNotFoundEvent
import sv.com.clip.learning.domain.repository.UserWordRepository
import sv.com.clip.learning.infrastructure.UserWordExclusionAdapter
import sv.com.clip.learning.infrastructure.repository.JpaUserWordExclusionRepository
import java.util.UUID

@Service
class TextAnalysisService(
  private val userWordRepository: UserWordRepository,
  private val dictionaryExternal: DictionaryExternal,
  private val exclusionAdapter: UserWordExclusionAdapter,
  private val eventPublisher: ApplicationEventPublisher,
) {

  @Transactional
  fun analyzeText(userId: UUID, rawText: String): AnalysisResult {

    // 1. Extraer palabras y limpiar
    val tokens = tokenize(rawText)
    val allUniqueWords = tokens.toSet() // palabras unicas a procesar

    // 2. Cargar exclusiones del usuario
//    val excluded = exclusionRepository.findAll().map { it.word }.toSet() // TODO: findByUserId
    val excluded = exclusionAdapter.findExclusions(userId)

    // 3. Filtrar palabras (solo enviamos al diccionario lo que no está excluido)
    val wordsToQuery = allUniqueWords.filter { it !in excluded }.toSet()


    // 4. Consultar Dictionary y Vocabulary...
    // -- Consultar qué palabras conoce el usuario en este módulo
//    val userKnownWords = userWordRepository.findByUserIdAndWordIdIn(userId, wordsInText)
//      .associateBy { it.wordId }
    println("UserId: $userId")
    val userKnownWords = userWordRepository.findAllByTermIn(wordsToQuery) // TODO: userId
//      .associateBy { it.lexicalEntryId }
      .associateBy { it.term }

    //  -- Consultar datos técnicos al módulo Dictionary
    val wordDetails = dictionaryExternal.getWords(wordsToQuery)

    // 5. Clasificar sin crear dependencia circular
    val classifiedWords = allUniqueWords.map { word ->
      val dictEntry = wordDetails.find { it.word == word }
      val userEntry = userKnownWords[word]
      when {
        word in excluded -> WordAnalysis(word, "Excluido", WordStatus.IGNORED)
        dictEntry == null -> WordAnalysis(word, "No encontrada en el diccionario", WordStatus.NOT_FOUND)
        else -> WordAnalysis(
          word = word,
          definition = dictEntry.definition,
          status = userEntry?.status ?: WordStatus.NEW
        )
      }
    }

    // 6. Generar el resumen (Summary)
    val summary = calculateSummary(classifiedWords, tokens.size)

    // 7. Detectar palabras que no están en dictDetails ni en excluded
    val notFoundTerms = allUniqueWords.filter { word ->
      word !in excluded && wordDetails.none { it.word == word }
    }.toSet()

    // 8. Publicar evento si hay palabras nuevas para el diccionario
    if (notFoundTerms.isNotEmpty()) {
      notFoundTerms.chunked(10).forEach { batch ->
        eventPublisher.publishEvent(WordsNotFoundEvent(batch.toSet()))
      }
    }
    return AnalysisResult(classifiedWords, summary)
  }
  private fun calculateSummary(classified: List<WordAnalysis>, totalTokens: Int): AnalysisSummary {
    val knownCount = classified.count { it.status == WordStatus.KNOWN }

    return AnalysisSummary(
      totalWords = totalTokens,
      uniqueWords = classified.size,
      knownCount = knownCount,
      learningCount = classified.count { it.status == WordStatus.RECOGNIZED || it.status == WordStatus.FAMILIAR },
      unknownCount = classified.count { it.status == WordStatus.NEW },
      percentageKnown = if (classified.isNotEmpty()) (knownCount.toDouble() / classified.size) * 100 else 0.0
    )
  }

  fun tokenize(rawText: String?): List<String> {
    if (rawText.isNullOrBlank()) return emptyList()

      return rawText.lowercase()
        // 1. Reemplazar saltos de línea y tabulaciones por espacios
        .replace(Regex("[\\n\\t\\r]"), " ")
        // 2. Eliminar caracteres que no sean letras o espacios
        // Usamos la categoría Unicode \p{L} para soportar letras con tildes y ñ
        // Mantenemos el apóstrofe (') por palabras en inglés como "don't" o "it's"
        .replace(Regex("[^\\p{L}'\\s]"), "")
        // 3. Dividir por uno o más espacios en blanco
        .split(Regex("\\s+"))
        // 4. Limpiar espacios extra y filtrar vacíos
        .map { it.trim() }
        .filter { it.length > 1 } // Opcional: ignorar letras sueltas como "a" o "y" según tu lógica
  }


}
