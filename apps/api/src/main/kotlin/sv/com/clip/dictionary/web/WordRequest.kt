package sv.com.clip.dictionary.web

import sv.com.clip.dictionary.domain.valueObjects.PartOfSpeech

data class WordRequest(
  val term: String,
  val lemma: String,
  val partOfSpeech: PartOfSpeech,
  //val CEFRLevel: CEFRLevel
)
