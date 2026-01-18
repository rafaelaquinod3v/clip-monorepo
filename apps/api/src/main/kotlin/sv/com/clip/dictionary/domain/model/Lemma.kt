package sv.com.clip.dictionary.domain.model

import sv.com.clip.dictionary.domain.valueObjects.PartOfSpeech

data class Lemma(
  val writtenForm: String,
  val partOfSpeech: PartOfSpeech,
  override val representations: List<FormRepresentation>
  ) : Form
