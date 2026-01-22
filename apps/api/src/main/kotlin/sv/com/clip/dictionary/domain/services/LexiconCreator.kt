package sv.com.clip.dictionary.domain.services

import sv.com.clip.dictionary.domain.model.Lexicon
import sv.com.clip.dictionary.domain.valueObjects.Language

interface LexiconCreator {
  fun create(lang: Language) : Lexicon
}
