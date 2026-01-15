package sv.com.clip.vocabulary.infrastructure.readmodel

import org.springframework.modulith.events.ApplicationModuleListener
import org.springframework.stereotype.Component
import sv.com.clip.vocabulary.domain.events.WordsAddedEvent

@Component
class WordReadModelProjector {

  @ApplicationModuleListener
  fun on(event: WordsAddedEvent) {
    println("--- Word read model projector ---")
    event.wordIds.forEach { wordId -> println(wordId) }
  }
}
