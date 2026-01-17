package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.AggregateRoot
import sv.com.clip.dictionary.domain.valueObjects.LanguageCode
import java.util.UUID

@AggregateRoot // gestiona los idiomas disponibles
class Language(val id: UUID, val code: LanguageCode, val name: String) {
}
