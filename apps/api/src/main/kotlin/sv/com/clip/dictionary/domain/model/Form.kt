package sv.com.clip.dictionary.domain.model

import com.fasterxml.uuid.Generators
import org.jmolecules.ddd.annotation.Entity
import org.jmolecules.ddd.types.Identifier
import java.util.UUID

@JvmInline
value class FormId(val value: UUID) : Identifier {
  companion object {
    // El generador de UUIDv7 usa un timestamp en los primeros 48 bits
    private val generator = Generators.timeBasedEpochGenerator()

    fun generate(): FormId {
      return FormId(generator.generate())
    }

    fun fromString(uuid: String): FormId {
      return FormId(UUID.fromString(uuid))
    }
  }
}

@Entity
class Form(
  val id: FormId = FormId.generate(),
  val writtenRepresentation: String,
  val script: String? = null,
  val phoneticIPA: String? = null,
  val audioURL: String? = null,
  ) {
}
