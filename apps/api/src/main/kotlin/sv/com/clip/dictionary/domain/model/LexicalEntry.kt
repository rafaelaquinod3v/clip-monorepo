package sv.com.clip.dictionary.domain.model

import com.fasterxml.uuid.Generators
import org.jmolecules.ddd.annotation.AggregateRoot
import org.jmolecules.ddd.types.Identifier
import sv.com.clip.dictionary.domain.valueObjects.PartOfSpeech
import java.util.UUID

@JvmInline
value class LexicalEntryId(val value: UUID) : Identifier {
  companion object {
    // El generador de UUIDv7 usa un timestamp en los primeros 48 bits
    private val generator = Generators.timeBasedEpochGenerator()

    fun generate(): LexicalEntryId {
      return LexicalEntryId(generator.generate())
    }

    fun fromString(uuid: String): LexicalEntryId {
      return LexicalEntryId(UUID.fromString(uuid))
    }
  }
}

// la palabra base en cualquier idioma.
// Es independiente del concepto; una palabra existe en un idioma y luego se vincula a conceptos.
// Lexical Markup Framework ISO 24613
@AggregateRoot // gestiona el termino el lemma y su fonetica -- es la entrada principal del vocabulario
class LexicalEntry(
  val lexicalEntryId: LexicalEntryId = LexicalEntryId.generate(),
  val sourceId: String?, // OMW WordNet 3.0
  val lemma: String, // bank
  val lexiconId: LexiconId,
  val partOfSpeech: PartOfSpeech,
  // Representa las formas de la palabra (variantes)
  private val _forms: List<Form> = mutableListOf(), // banks
  // bank, banks, banked, banking
  // Representa los significados asociados
  private val _senses: MutableList<Sense> = mutableListOf(),
  // noun: financial institution
  // verb: to deposit money
) {
  val senses: List<Sense> get() = _senses.toList()
  val forms: List<Form> get() = _forms.toList()
}
