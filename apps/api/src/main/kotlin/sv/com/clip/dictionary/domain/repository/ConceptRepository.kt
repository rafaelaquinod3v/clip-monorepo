package sv.com.clip.dictionary.domain.repository

import sv.com.clip.dictionary.domain.model.Concept

interface ConceptRepository {
  fun save(concept: Concept) : Concept
  fun findAll() : List<Concept>
  fun findByIli(ili: String) : Concept?
}
