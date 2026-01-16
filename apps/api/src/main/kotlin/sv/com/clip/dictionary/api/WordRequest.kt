package sv.com.clip.dictionary.api

import sv.com.clip.dictionary.domain.valueObject.LanguageLevel
import sv.com.clip.dictionary.domain.valueObject.PartOfSpeech

data class WordRequest(
  val term: String,
  val lemma: String,
  val partOfSpeech: PartOfSpeech,
  val languageLevel: LanguageLevel
)
