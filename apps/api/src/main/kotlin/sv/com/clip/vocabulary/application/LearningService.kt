package sv.com.clip.vocabulary.application

import sv.com.clip.library.domain.ContentProcessor
import sv.com.clip.vocabulary.domain.UserWord
import sv.com.clip.vocabulary.domain.UserWordRepository
import java.util.UUID

class LearningService(
    private val wordRepository: UserWordRepository,
    private val contentProcessor: ContentProcessor
) {

  //Caso de uso: crear un nuevo "LingQ" (palabra guardada)
  //@Transactional // aun no tengo Data como dependencia
  suspend fun createLingQ(userId: UUID, term: String, translation: String, sentence: String): UserWord {
    val existing = wordRepository.findByUserIdAndTerm(userId, term)
    if (existing != null) return existing

    val newWord = UserWord(userId = userId, term = term, translation = translation, contextSentence = sentence)
    return wordRepository.save(newWord)
  }

    fun processContent(url: String): String {
    return "TODO"
  }

}
