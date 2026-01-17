package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.Entity
import java.util.UUID
// EXplicacion de concepto en cualquier idioma
// Pertenece exclusivamente a un Concept
@Entity
class Definition(val concept_id: UUID, val language_id: UUID, val text: String) {
}
