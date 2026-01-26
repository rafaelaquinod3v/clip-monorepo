package sv.com.clip.dictionary.infrastructure

import org.springframework.stereotype.Repository
import sv.com.clip.dictionary.domain.model.Concept
import sv.com.clip.dictionary.domain.repository.ConceptRepository
import sv.com.clip.dictionary.infrastructure.persistence.jpa.ConceptEntity
import sv.com.clip.dictionary.infrastructure.persistence.repository.JpaConceptEntityRepository

@Repository
class ConceptRepositoryAdapter(
  private val conceptRepository : JpaConceptEntityRepository
) : ConceptRepository {

  override fun save(concept: Concept): Concept {
    return conceptRepository.save(ConceptEntity.fromDomain(concept)).toDomain()
  }

  override fun findAll(): List<Concept> {
    return conceptRepository.findAll().map(ConceptEntity::toDomain)
  }

  override fun findByIli(ili: String): Concept? {
    return conceptRepository.findByIli(ili).toDomain()
  }

  override fun existsByIli(ili: String): Boolean {
    return conceptRepository.existsByIli(ili)
  }
}
