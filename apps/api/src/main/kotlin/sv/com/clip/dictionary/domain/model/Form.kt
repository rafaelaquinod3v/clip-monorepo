package sv.com.clip.dictionary.domain.model

class Form(
  val writtenRepresentation: String,
  val isLemma: Boolean = false,
  val script: String?,
  val phoneticIPA: String? = null,
  val audioURL: String? = null,
  ) {
}
