package sv.com.clip.dictionary.domain.model

sealed interface Form {
  val representations: List<FormRepresentation>
}
