package sv.com.clip.dictionary.domain.model

import org.jmolecules.ddd.annotation.Entity
import sv.com.clip.dictionary.domain.valueObjects.LanguageLevel
import sv.com.clip.dictionary.domain.valueObjects.PartOfSpeech
import java.util.UUID

// une una palabra especifica con un significado
// 	Representa el "Sense". Vive dentro del ciclo de vida de una Word.
@Entity
class WordConcept(
  val wordID: UUID,
  val conceptID: UUID,
  val partOfSpeech: PartOfSpeech,
  val languageLevel: LanguageLevel
) {

}
