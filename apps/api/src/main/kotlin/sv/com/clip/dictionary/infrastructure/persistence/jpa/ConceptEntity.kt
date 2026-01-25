package sv.com.clip.dictionary.infrastructure.persistence.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "concepts")
class ConceptEntity(
  @Id
  @Column(name = "ili")
  val ili: String
) {}
