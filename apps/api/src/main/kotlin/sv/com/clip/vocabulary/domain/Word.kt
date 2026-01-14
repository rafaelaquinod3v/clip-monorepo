package sv.com.clip.vocabulary.domain

import sv.com.clip.vocabulary.domain.valueObject.LanguageLevel
import sv.com.clip.vocabulary.domain.valueObject.PartOfSpeech
import java.util.UUID

class Word(
  val id: UUID = UUID.randomUUID(),
  val term: String,
  val lemma: String,
  val partOfSpeech: PartOfSpeech,
  val languageLevel: LanguageLevel,
) {

  fun isVariationOf(other: Word): Boolean {
    return other.lemma == lemma || other.term == term
  }

  fun hasSameRoot(other: Word): Boolean {
    return other.lemma == lemma
  }
}
