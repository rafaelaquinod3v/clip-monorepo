package sv.com.clip.vocabulary.domain

interface WordRepository {
  fun findByUserIdAndTerm(term: String): Word
  fun save(word: Word): Word
}
