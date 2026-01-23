package sv.com.clip.dictionary.domain.repository

import org.jmolecules.ddd.annotation.Repository
import sv.com.clip.dictionary.domain.model.LexicalEntry
import sv.com.clip.dictionary.domain.model.Lexicon
import sv.com.clip.dictionary.domain.model.LexiconId
import sv.com.clip.dictionary.domain.valueObjects.Language

@Repository
internal interface LexiconRepository {
  fun save(lexicon: Lexicon) : Lexicon
  fun existsByLanguage(language: Language): Boolean
}
