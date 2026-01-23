package sv.com.clip.dictionary.domain.model

import com.fasterxml.uuid.Generators
import org.jmolecules.ddd.annotation.AggregateRoot
import org.jmolecules.ddd.types.Identifier
import sv.com.clip.dictionary.domain.valueObjects.Language
import java.util.UUID

@JvmInline
value class LexiconId(val uuid: UUID): Identifier {
  companion object {
    // El generador de UUIDv7 usa un timestamp en los primeros 48 bits
    private val generator = Generators.timeBasedEpochGenerator()

    fun generate() : LexiconId {
      return LexiconId(generator.generate())
    }

    fun fromString(uuid: String) : LexiconId {
      return LexiconId(UUID.fromString(uuid))
    }
  }
}

@AggregateRoot
class Lexicon(
  val id: LexiconId = LexiconId.generate(),
  val lang: Language
) {}
