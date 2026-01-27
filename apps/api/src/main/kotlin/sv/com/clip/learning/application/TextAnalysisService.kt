package sv.com.clip.learning.application

import org.springframework.stereotype.Service
import sv.com.clip.dictionary.api.DictionaryExternal
import sv.com.clip.learning.domain.WordStatus
import sv.com.clip.learning.domain.repository.UserWordRepository
import java.util.UUID

@Service
class TextAnalysisService(
  private val userWordRepository: UserWordRepository,
  private val dictionaryExternal: DictionaryExternal,
) {

  fun analyzeText(userId: UUID, rawText: String): AnalysisResult {
    // 1. Extraer palabras únicas (limpieza básica)
    val wordsInText = rawText.lowercase()
      .split(Regex("\\W+"))
      .filter { it.isNotBlank() }
      .toSet()

    // 2. Consultar qué palabras conoce el usuario en este módulo
//    val userKnownWords = userWordRepository.findByUserIdAndWordIdIn(userId, wordsInText)
//      .associateBy { it.wordId }
println("UserId: $userId")
    val userKnownWords = userWordRepository.findAllByTermIn(wordsInText)
      .associateBy { it.lexicalEntryId }

    // 3. Consultar datos técnicos al módulo Dictionary
    val wordDetails = dictionaryExternal.getWords(wordsInText)

    // 4. Clasificar sin crear dependencia circular
    val classifiedWords = wordDetails.map { detail ->
      val status = userKnownWords[detail.id]?.status ?: WordStatus.NEW
      WordAnalysis(
        word = detail.word,
        definition = detail.definition,
        status = status,
        lemma = detail.word
      )
    }

    // Generar el resumen (Summary)
    val summary = AnalysisSummary(
      totalWords = rawText.split(Regex("\\W+")).size,
      uniqueWords = classifiedWords.size,
      knownCount = classifiedWords.count { it.status == WordStatus.KNOWN },
      learningCount = classifiedWords.count { it.status == WordStatus.RECOGNIZED || it.status == WordStatus.FAMILIAR },
      unknownCount = classifiedWords.count { it.status == WordStatus.NEW },
      percentageKnown = if (classifiedWords.isNotEmpty())
        (classifiedWords.count { it.status == WordStatus.KNOWN }.toDouble() / classifiedWords.size) * 100
      else 0.0
    )
    return AnalysisResult(classifiedWords, summary)
  }
}
