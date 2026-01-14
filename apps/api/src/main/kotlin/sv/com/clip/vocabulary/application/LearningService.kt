package sv.com.clip.vocabulary.application

import sv.com.clip.vocabulary.domain.Word
import sv.com.clip.vocabulary.domain.WordRepository
import sv.com.clip.vocabulary.domain.valueObject.LanguageLevel
import sv.com.clip.vocabulary.domain.valueObject.PartOfSpeech
import java.util.UUID

class LearningService(
    private val wordRepository: WordRepository,

) {
 // private val contentProcessor: ContentProcessor
  //Caso de uso: crear un nuevo "LingQ" (palabra guardada)
  //@Transactional // aun no tengo Data como dependencia
  suspend fun createLingQ(userId: UUID, term: String, translation: String, sentence: String): Word {
    val existing = wordRepository.findByUserIdAndTerm(term)
    if (existing != null) return existing

    val newWord = Word(term = term, lemma = "hello", partOfSpeech = PartOfSpeech.ADV, languageLevel = LanguageLevel.A1)
    return wordRepository.save(newWord)
  }

  fun processContent(url: String): String {
    return "TODO"
  }

}
