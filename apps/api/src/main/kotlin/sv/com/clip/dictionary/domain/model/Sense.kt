package sv.com.clip.dictionary.domain.model

import com.fasterxml.uuid.Generators
import org.jmolecules.ddd.annotation.Entity
import org.jmolecules.ddd.types.Identifier
import sv.com.clip.dictionary.domain.valueObjects.CEFRLevel
import java.util.UUID

//Etiquetado POS automático: Al importar frases, usa modelos de procesamiento de lenguaje natural (NLP) para identificar automáticamente qué POS se está usando en ese contexto específico.
//Carga selectiva: Si tu usuario encuentra la palabra "back" en una lección sobre anatomía, tu app debe consultar la API y guardar en tu base de datos únicamente el sentido de sustantivo (espalda). Así, mantienes tu base de datos limpia y enfocada en lo que el usuario realmente está aprendiendo.
// CEFR Oxford Learner's Dictionary API o Cambridge Dictionary API
// spaCy POS
// id: 50, word_id: 1, pos: noun, cefr: A1, definition: financial institution
@JvmInline
value class SenseId(val value: UUID) : Identifier {
  companion object {
    // El generador de UUIDv7 usa un timestamp en los primeros 48 bits
    private val generator = Generators.timeBasedEpochGenerator()

    fun generate(): SenseId {
      return SenseId(generator.generate())
    }

    fun fromString(uuid: String): SenseId {
      return SenseId(UUID.fromString(uuid))
    }
  }
}
// Sentido: Contenedor para las definiciones y el contexto semántico
// que desambiguan el significado de la forma.
@Entity
class Sense(
  val id: SenseId = SenseId.generate(),
  val sourceId: String? = null, // OMW WordNet 3.0
  val conceptIli: String, // FK: Referencia obligatoria a Concept.iliId
  val gloss: String? = null, // financial institution, bank (money)
  val definition: String? = null, // An establishment for the custody, loan, or exchange of money.
  val examples: List<UsageExample> = mutableListOf(),
) {
}
