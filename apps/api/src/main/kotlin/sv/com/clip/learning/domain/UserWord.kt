package sv.com.clip.learning.domain

import org.jmolecules.ddd.annotation.AggregateRoot
import java.util.UUID

@AggregateRoot
class UserWord(
  val id: UUID = UUID.randomUUID(),
  val userId: UUID? = null,
  val term: String,
  var status: WordStatus = WordStatus.NEW,
  val isManualLexicalEntry: Boolean = false, // si esto es verdadero, se deben llenar los siguientes
  val customDefinition: String? = null,
  val lexicalEntryId: UUID? = null, // referencia ligera (id) al dictionary
  val sourceLanguage: String? = null,
  val targetLanguage: String? = null,
  val phoneticIpa: String? = null,
  val audioUrl: String? = null,
  var translations: MutableList<String> = mutableListOf()
) {
  fun upgradeStatus() {
    this.status = when (status) {
      WordStatus.NEW -> WordStatus.RECOGNIZED
      WordStatus.RECOGNIZED -> WordStatus.FAMILIAR
      WordStatus.FAMILIAR -> WordStatus.KNOWN
      WordStatus.KNOWN -> WordStatus.LEARNED
      else -> {
        WordStatus.valueOf(status.name)
      }
//      WordStatus.LEARNED -> WordStatus.LEARNED
//      WordStatus.NOT_FOUND -> WordStatus.NOT_FOUND
//      WordStatus.IGNORED -> WordStatus.IGNORED
    }
  }

}
