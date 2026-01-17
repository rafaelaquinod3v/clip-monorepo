package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.Entity
import java.util.UUID

// sense_id: 50, text_en: i need to go to the bank, text_target: necesito ir al banco
// sense_id: 50, text_en: the bank is closed, text_target: el banco esta cerrado
// audio_url: idioma que se estudia
@JvmInline
value class ExampleId(val value: UUID)
@Entity // contenedor de traducciones que pertenecen a un Concept
class Example(
  val id: ExampleId,
  val audioURL: String,
  val translations: List<ExampleTranslation>
  )
  //val concept_id: UUID, )
{

}

// 	Un campo tipo JSONB (muy común en 2026) que guarda las traducciones del ejemplo:
// 	{"es": "El banco está cerrado", "fr": "La banque est fermée"}

// Búsqueda Global: Puedes indexar la tabla Example por separado para permitir funciones de
// "buscar frases que contengan esta palabra", lo cual es una función premium muy valorada.
