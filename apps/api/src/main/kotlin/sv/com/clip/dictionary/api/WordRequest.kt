package sv.com.clip.dictionary.api

import sv.com.clip.dictionary.domain.valueObjects.LanguageLevel
import sv.com.clip.dictionary.domain.valueObjects.PartOfSpeech

data class WordRequest(
  val term: String,
  val lemma: String,
  val partOfSpeech: PartOfSpeech,
  val languageLevel: LanguageLevel
)
