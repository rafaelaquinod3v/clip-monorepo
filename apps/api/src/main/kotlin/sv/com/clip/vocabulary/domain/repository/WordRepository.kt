package sv.com.clip.vocabulary.domain.repository

import sv.com.clip.vocabulary.domain.model.Word
import sv.com.clip.vocabulary.domain.model.WordIdentifier
import sv.com.clip.vocabulary.domain.valueObject.LanguageLevel

interface WordRepository {
  fun findById(id: WordIdentifier): Word?
  fun saveAll(words: List<Word>): List<Word>
  fun save(word: Word): Word
  fun save(word: Word, embedding: FloatArray?): Word
  fun findAll(): List<Word>
  fun findByTermSimilarity(term: String): List<Word>
  fun findAllByLanguageLevel(languageLevel: LanguageLevel): List<Word>
}
