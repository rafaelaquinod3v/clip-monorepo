package sv.com.clip.dictionary.domain.model

data class FormRepresentation(
  val writtenForm: String,
  val script: String?,
  val phoneticIpa: String?,
  val audioURL: String?
)
