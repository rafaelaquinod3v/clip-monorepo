package sv.com.clip.dictionary.infrastructure;

import org.springframework.stereotype.Repository
import sv.com.clip.dictionary.domain.model.Word
import sv.com.clip.dictionary.domain.model.WordIdentifier
import sv.com.clip.dictionary.domain.repository.WordRepository
import sv.com.clip.dictionary.domain.valueObjects.CEFRLevel

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

  override fun findAllById(ids: List<WordIdentifier>): List<Word> {
    return repo.findAllById(ids.map { it.uuid }).map { it.toDomain() }
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

 //7 override fun findAllByLanguageLevel(CEFRLevel: CEFRLevel): List<Word> {
    //return repo.findAllByLanguageLevel(CEFRLevel).map { it.toDomain() }
  //
  //}
}
