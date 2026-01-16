package sv.com.clip.dictionary.domain.repository

import sv.com.clip.dictionary.domain.model.Word
import sv.com.clip.dictionary.domain.model.WordIdentifier
import sv.com.clip.dictionary.domain.valueObject.LanguageLevel

interface WordRepository {
  fun findById(id: WordIdentifier): Word?
  fun saveAll(words: List<Word>): List<Word>
  fun save(word: Word): Word
  fun save(word: Word, embedding: FloatArray?): Word
  fun findAll(): List<Word>
  fun findAllById(ids: List<WordIdentifier>): List<Word>
  fun findByTermSimilarity(term: String): List<Word>
  fun findAllByLanguageLevel(languageLevel: LanguageLevel): List<Word>
}
