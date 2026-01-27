package sv.com.clip.learning.domain.events

data class WordsNotFoundEvent(
  val words: Set<String>,
)
