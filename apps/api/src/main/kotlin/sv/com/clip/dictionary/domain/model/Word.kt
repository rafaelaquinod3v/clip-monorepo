package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.AggregateRoot
import org.jmolecules.ddd.types.Identifier
import sv.com.clip.dictionary.domain.valueObjects.LanguageLevel
import sv.com.clip.dictionary.domain.valueObjects.PartOfSpeech
import java.util.UUID

@JvmInline
value class WordIdentifier(val uuid: UUID = UUID.randomUUID()): Identifier {}
// spaCy lemma
// id: 1, lemma: bank, phonetic_ipa: "$%%$#, audio_world_ul: http://elevenlabs?
@Deprecated("now")
@AggregateRoot
class Word(
  val id: WordIdentifier = WordIdentifier(),
  var term: String,
  var lemma: String,
  var partOfSpeech: PartOfSpeech, // remove
  var languageLevel: LanguageLevel, // remove
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
