package sv.com.clip.dictionary.infrastructure

import org.springframework.stereotype.Repository
import sv.com.clip.dictionary.api.WordDTO
import sv.com.clip.dictionary.domain.model.LexicalEntry
import sv.com.clip.dictionary.domain.repository.LexicalEntryRepository
import sv.com.clip.dictionary.infrastructure.persistence.jpa.LexicalEntryEntity
import sv.com.clip.dictionary.infrastructure.persistence.repository.JpaLexicalEntryEntityRepository

@Repository
class LexicalEntryRepositoryAdapter(
  private val jpaRepository: JpaLexicalEntryEntityRepository
) : LexicalEntryRepository {

  override fun save(lexicalEntry: LexicalEntry): LexicalEntry {
    return jpaRepository.save(LexicalEntryEntity.fromDomain(lexicalEntry)).toDomain()
  }

  override fun findAll(): List<LexicalEntry> {
    return jpaRepository.findAll().map { it.toDomain() }
  }

  override fun findAllByLemmaIn(lemmas: Set<String>): List<LexicalEntry> {
    return jpaRepository.findAllByLemmaInWithSenses(lemmas).map { it.toDomain() }
  }

  override fun findAllByLemmaInWithDetails(lemma: Set<String>): List<LexicalEntry> {
    return jpaRepository.findAllByLemmaInWithDetails(lemma).map { it.toDomain() }
  }

  override fun findProjectionsByLemmaIn(lemmas: Set<String>): List<WordDTO> {
    return jpaRepository.findProjectionsByLemmaIn(lemmas)
  }

  override fun saveAll(list: List<LexicalEntry>): List<LexicalEntry> {
    return jpaRepository.saveAll(list.map { LexicalEntryEntity.fromDomain(it) }).map { it.toDomain() }
  }
}
