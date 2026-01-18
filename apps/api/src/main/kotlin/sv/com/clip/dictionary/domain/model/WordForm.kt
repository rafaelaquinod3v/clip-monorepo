package sv.com.clip.dictionary.domain.model

data class WordForm(
  override val representations: List<FormRepresentation>
) : Form
