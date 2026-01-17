package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.AggregateRoot
import java.util.UUID

@JvmInline
value class ConceptId(val value: UUID)

// el puente invisible entre dos idiomas
@AggregateRoot // Corazon controla Definitions, Examples
class Concept(
  val id: ConceptId,
  val universalCode: String,
  private val _definitions: MutableList<Definition> = mutableListOf(),
  private val _examples: MutableList<Example> = mutableListOf(),
) {
  val definitions: List<Definition> get() = _definitions
  val examples: List<Example> get() = _examples

  fun addDefinition(languageId: UUID, text: String) {
    _definitions.add(Definition(this.id.value ,languageId, text))
  }

  fun addExample(exampleId: UUID) {
    // _examples.add(example)
  }
}
