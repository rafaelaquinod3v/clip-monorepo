package sv.com.clip.dictionary.domain.model

import sv.com.clip.dictionary.domain.valueObjects.LanguageCode
import java.util.UUID

// sense_id: 50, lang: es, text: banco
// sense_id: 50, lang: pt, text: banco // si se lanza en portugues o sea un portugues aprendiendo ingles
@Deprecated("now")
class Translation(val id: UUID, val senseId: UUID, val lang: LanguageCode, val text: String) {
}
