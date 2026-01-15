package sv.com.clip.vocabulary.infrastructure;

import org.springframework.stereotype.Repository
import sv.com.clip.vocabulary.domain.model.Word
import sv.com.clip.vocabulary.domain.model.WordIdentifier
import sv.com.clip.vocabulary.domain.repository.WordRepository
import sv.com.clip.vocabulary.domain.valueObject.LanguageLevel

@Repository
class WordRepositoryAdapter (private val repo: JpaWordRepository, private val embedding: DjlEmbeddingAdapter): WordRepository {
  override fun findById(id: WordIdentifier): Word? {
    return repo.findById(id.uuid)
            .map { it.toDomain() }
            .orElse(null)
  }

  override fun saveAll(words: List<Word>): List<Word> {
    return words.asSequence()
      .map { WordEntity.fromDomain(it) }
      .toList() // Spring Data saveAll requiere un Iterable
      .let { repo.saveAll(it) }
      .map { it.toDomain() }
  }

  override fun save(word: Word): Word {
    return repo.save(WordEntity.fromDomain(word)).toDomain()
  }

  override fun save(word: Word, embedding: FloatArray?): Word {
    val wordEntity = WordEntity.fromDomain(word)
    wordEntity.embedding = embedding
    return repo.save(wordEntity).toDomain()
  }

  override fun findAll(): List<Word> {
    return repo.findAll().map { it.toDomain() }
  }

  override fun findByTermSimilarity(term: String): List<Word> {
    // Convierte el array a formato: "[0.123,0.456,0.789]"
    val queryVector = embedding.calculate(term).joinToString(
      separator = ",",
      prefix = "[",
      postfix = "]"
    )

    // Llama al repositorio pasando el String
    return repo.findByTermSimilarity(queryVector, 5).map { it.toDomain() }
  }

  override fun findAllByLanguageLevel(languageLevel: LanguageLevel): List<Word> {
    return repo.findAllByLanguageLevel(languageLevel).map { it.toDomain() }
  }
}
