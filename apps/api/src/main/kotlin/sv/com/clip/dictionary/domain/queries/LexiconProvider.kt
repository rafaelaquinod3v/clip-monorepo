package sv.com.clip.dictionary.domain.queries

import sv.com.clip.dictionary.domain.model.Lexicon
import sv.com.clip.dictionary.domain.valueObjects.Language

interface LexiconProvider {
  fun findByLang(language: Language): Lexicon? // Podria ser un DTO de lectura (Data Projection)
  fun findAll(): List<Lexicon>
}
