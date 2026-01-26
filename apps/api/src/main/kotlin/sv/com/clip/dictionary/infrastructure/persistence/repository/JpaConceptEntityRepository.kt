package sv.com.clip.dictionary.infrastructure.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import sv.com.clip.dictionary.infrastructure.persistence.jpa.ConceptEntity

interface JpaConceptEntityRepository : JpaRepository<ConceptEntity, String> {
  fun findByIli(ili: String) : ConceptEntity
  fun existsByIli(ili: String) : Boolean
}
