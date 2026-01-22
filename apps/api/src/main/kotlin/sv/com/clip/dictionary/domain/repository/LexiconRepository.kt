package sv.com.clip.dictionary.domain.repository

import org.springframework.stereotype.Repository
import sv.com.clip.dictionary.domain.model.LanguageId
import sv.com.clip.dictionary.domain.model.LexicalEntry

@Repository
class LexiconRepository {
  fun findEntriesByPivotsAndLanguage(pivots: List<String>, language : LanguageId): List<LexicalEntry> {
    return emptyList()
  }
}
