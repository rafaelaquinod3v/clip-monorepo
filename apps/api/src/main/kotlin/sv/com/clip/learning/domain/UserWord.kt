package sv.com.clip.learning.domain

import org.jmolecules.ddd.annotation.AggregateRoot
import java.util.UUID

@AggregateRoot
class UserWord(
    val id: UUID = UUID.randomUUID(),
    val userId: UUID? = null,
    val lexicalEntryId: UUID? = null, // referencia ligera (id) al dictionary
    val term: String,
    var translation: String? = null,
    var status: WordStatus = WordStatus.NEW,
    val contextSentence: String? = null,
) {
  fun upgradeStatus() {
    if (status != WordStatus.KNOWN) {
      this.status = WordStatus.entries[status.ordinal + 1]
    }
  }
}
