package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.AggregateRoot

@AggregateRoot
class Concept(
  val ili: String, // PK: Identificador único universal (ej: "ili:3001")
  ) {}
