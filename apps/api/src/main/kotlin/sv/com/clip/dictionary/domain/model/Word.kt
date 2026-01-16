package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.AggregateRoot
import org.jmolecules.ddd.types.Identifier
import sv.com.clip.dictionary.domain.valueObject.LanguageLevel
import sv.com.clip.dictionary.domain.valueObject.PartOfSpeech
import java.util.UUID

@JvmInline
value class WordIdentifier(val uuid: UUID = UUID.randomUUID()): Identifier {}

@AggregateRoot
class Word(
  val id: WordIdentifier = WordIdentifier(),
  var term: String,
  var lemma: String,
  var partOfSpeech: PartOfSpeech,
  var languageLevel: LanguageLevel,
) {

  fun isVariationOf(other: Word): Boolean {
    return other.lemma == lemma || other.term == term
  }

  fun hasSameRoot(other: Word): Boolean {
    return other.lemma == lemma
  }

  override fun toString(): String {
    return "Word(id=$id, term='$term', lemma='$lemma', partOfSpeech=$partOfSpeech, languageLevel=$languageLevel)"
  }

}
