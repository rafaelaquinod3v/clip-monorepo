package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.AggregateRoot
import java.util.UUID

@JvmInline
value class Word2Id(val value: UUID)

// la palabra base en cualquier idioma.
// Es independiente del concepto; una palabra existe en un idioma y luego se vincula a conceptos.
@AggregateRoot // gestiona el termino el lemma y su fonetica -- es la entrada principal del vocabulario
class Word2(
  val wordId: Word2Id,
  val languageId: UUID,
  val lemma: String,
  val phonetic: String,
  private val _wordConcepts: MutableList<WordConcept> = mutableListOf(),
) {
  val wordConcepts: List<WordConcept> get() = _wordConcepts

}
