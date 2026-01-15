package sv.com.clip.vocabulary.domain.events

import sv.com.clip.vocabulary.domain.model.WordIdentifier
import java.time.Instant

data class WordsAddedEvent(
  val wordIds: List<WordIdentifier>,
  val occurredAt: Instant = Instant.now(),
)
