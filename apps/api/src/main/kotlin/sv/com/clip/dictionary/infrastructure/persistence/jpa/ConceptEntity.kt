package sv.com.clip.dictionary.infrastructure.persistence.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import sv.com.clip.dictionary.domain.model.Concept

@Entity
@Table(name = "concepts")
class ConceptEntity(
  @Id
  @Column(name = "ili")
  val ili: String
) {
  companion object {
    fun fromDomain(concept: Concept): ConceptEntity {
      return ConceptEntity(concept.ili)
    }
  }
  fun toDomain(): Concept {
    return Concept(this.ili)
  }
}
