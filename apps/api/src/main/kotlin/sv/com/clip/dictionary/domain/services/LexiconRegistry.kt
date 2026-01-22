package sv.com.clip.dictionary.domain.services

import org.jmolecules.ddd.annotation.Service
import sv.com.clip.dictionary.domain.model.Lexicon
import sv.com.clip.dictionary.domain.repository.LexiconRepository
import sv.com.clip.dictionary.domain.valueObjects.Language

@Service
internal class LexiconRegistry(
  private val lexiconRepository: LexiconRepository
) : LexiconCreator {

  override fun create(lang: Language): Lexicon {
    // La regla de negocio reside aquí
    if (lexiconRepository.existsByLanguage(lang)) {
      throw IllegalArgumentException("Ya existe un Lexicon para el lenguaje: ${lang.value}")
    }
    val newLexicon = Lexicon(lang = lang)
    return lexiconRepository.save(newLexicon)
  }
}
