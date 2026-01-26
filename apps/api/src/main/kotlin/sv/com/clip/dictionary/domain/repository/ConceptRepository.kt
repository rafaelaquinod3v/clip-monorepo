package sv.com.clip.dictionary.domain.repository

import org.jmolecules.ddd.annotation.Repository
import sv.com.clip.dictionary.domain.model.Concept

@Repository
interface ConceptRepository {
  fun save(concept: Concept) : Concept
  fun findAll() : List<Concept>
  fun findByIli(ili: String) : Concept?
  fun existsByIli(ili: String) : Boolean
}
