package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.AggregateRoot
import java.util.UUID

@JvmInline
value class LexicalEntryId(val value: UUID)

// la palabra base en cualquier idioma.
// Es independiente del concepto; una palabra existe en un idioma y luego se vincula a conceptos.
// Lexical Markup Framework ISO 24613
@AggregateRoot // gestiona el termino el lemma y su fonetica -- es la entrada principal del vocabulario
class LexicalEntry(
  val lexicalEntryId: LexicalEntryId,
  val lemma: Lemma,
  val languageId: UUID,
  val forms: List<Form>, // Lista unificada de lemma y wordForms
  private val _senses: MutableList<Sense> = mutableListOf(),
) {
  val senses: List<Sense> get() = _senses.toList()
  fun getLemma(): Lemma = forms.filterIsInstance<Lemma>().first()

}
