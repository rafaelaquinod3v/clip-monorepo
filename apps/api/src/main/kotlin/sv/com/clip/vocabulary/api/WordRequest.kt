package sv.com.clip.vocabulary.api

import sv.com.clip.vocabulary.domain.valueObject.LanguageLevel
import sv.com.clip.vocabulary.domain.valueObject.PartOfSpeech

data class WordRequest(
  val term: String,
  val lemma: String,
  val partOfSpeech: PartOfSpeech,
  val languageLevel: LanguageLevel
)
