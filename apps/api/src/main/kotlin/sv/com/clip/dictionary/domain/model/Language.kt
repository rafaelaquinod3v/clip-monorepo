package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.AggregateRoot
import sv.com.clip.dictionary.domain.valueObjects.LanguageCode
import java.util.UUID

@JvmInline
value class LanguageId(val value: UUID)

@AggregateRoot // gestiona los idiomas disponibles
class Language(val id: LanguageId, val code: String, val value: String) {
}
