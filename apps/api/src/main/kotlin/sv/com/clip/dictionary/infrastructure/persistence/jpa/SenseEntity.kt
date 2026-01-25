package sv.com.clip.dictionary.infrastructure.persistence.jpa

import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import sv.com.clip.dictionary.infrastructure.persistence.jpa.UsageExampleEmbeddable
import java.util.UUID

@Entity
@Table(name = "lexical_entry_senses")
class SenseEntity(
    @Id var id: UUID,
    var sourceId: String? = null,

  // DDD: Referencia a otro agregado por ID (Concept.ili)
  // Suponiendo que ConceptIli es otra value class o un String (iliId)
    @Column(name = "concept_ili", nullable = false)
  val conceptIli: String,

    @Column(columnDefinition = "TEXT")
  val gloss: String? = null,
    @Column(columnDefinition = "TEXT")
  val definition: String? = null,
  // Mapeo de Value Objects (Ejemplos)
    @ElementCollection
  @CollectionTable(name = "sense_examples", joinColumns = [JoinColumn(name = "sense_id")])
  private val _examples: MutableList<UsageExampleEmbeddable> = mutableListOf(),

  // Relación con la raíz del agregado
    @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lexical_entry_id")
  var lexicalEntryEntity: LexicalEntryEntity? = null
) {
  val examples: List<UsageExampleEmbeddable> get() = _examples.toList()
}
