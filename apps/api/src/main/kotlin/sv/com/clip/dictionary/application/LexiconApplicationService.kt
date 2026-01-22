package sv.com.clip.dictionary.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sv.com.clip.dictionary.domain.model.Lexicon
import sv.com.clip.dictionary.domain.services.LexiconRegistry
import sv.com.clip.dictionary.domain.valueObjects.Language

@Service
internal class LexiconApplicationService(
  private val registry: LexiconRegistry
) {

  @Transactional
  fun createLexicon(language: Language): Lexicon {
    return this.registry.create(language)
  }
}
