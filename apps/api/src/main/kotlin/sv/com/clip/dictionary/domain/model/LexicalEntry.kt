package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.AggregateRoot
import sv.com.clip.dictionary.domain.valueObjects.PartOfSpeech
import java.util.UUID

@JvmInline
value class LexicalEntryId(val value: UUID)

// la palabra base en cualquier idioma.
// Es independiente del concepto; una palabra existe en un idioma y luego se vincula a conceptos.
// Lexical Markup Framework ISO 24613
@AggregateRoot // gestiona el termino el lemma y su fonetica -- es la entrada principal del vocabulario
class LexicalEntry(
  val lexicalEntryId: LexicalEntryId,
  val sourceId: String, // OMW WordNet 3.0
  val lemma: String,
  val languageId: LanguageId,
  val partOfSpeech: PartOfSpeech,
  // Representa las formas de la palabra (p. ej. lema, variantes)
  private val _forms: List<Form> = mutableListOf(), // Lista unificada de lemma y wordForms // bank, banks
  // bank, banks, banked, banking
  // Representa los significados asociados
  private val _senses: MutableList<Sense> = mutableListOf(),
  // noun: financial institution
  // verb: to deposit money
) {
  val senses: List<Sense> get() = _senses.toList()
  val forms: List<Form> get() = _forms.toList()
}
