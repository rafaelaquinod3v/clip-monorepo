package sv.com.clip.dictionary.domain.events

import sv.com.clip.dictionary.domain.model.WordIdentifier
import java.time.Instant

data class WordsAddedEvent(
  val wordIds: List<WordIdentifier>,
  val occurredAt: Instant = Instant.now(),
)
