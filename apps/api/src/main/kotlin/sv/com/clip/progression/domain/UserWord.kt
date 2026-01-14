package sv.com.clip.progression.domain

import sv.com.clip.progression.WordStatus
import java.util.UUID

class UserWord(
    val id: UUID = UUID.randomUUID(),
    val userId: UUID,
    val term: String,
    var translation: String,
    var status: WordStatus = WordStatus.NEW,
    val contextSentence: String
) {
  fun upgradeStatus() {
    if (status != WordStatus.KNOWN) {
      this.status = WordStatus.entries[status.ordinal + 1]
    }
  }
}
