package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.AggregateRoot
import org.jmolecules.ddd.types.Identifier
import sv.com.clip.dictionary.domain.valueObjects.Language
import java.util.UUID

@JvmInline
value class LexiconId(val uuid: UUID = UUID.randomUUID()): Identifier {}

@AggregateRoot
class Lexicon(
  val id: LexiconId = LexiconId(),
  val lang: Language
) {}
