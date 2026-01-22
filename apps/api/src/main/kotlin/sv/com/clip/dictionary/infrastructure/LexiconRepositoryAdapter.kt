package sv.com.clip.dictionary.infrastructure

import org.springframework.stereotype.Repository
import sv.com.clip.dictionary.domain.model.LexicalEntry
import sv.com.clip.dictionary.domain.model.Lexicon
import sv.com.clip.dictionary.domain.model.LexiconId
import sv.com.clip.dictionary.domain.repository.LexiconRepository
import sv.com.clip.dictionary.domain.valueObjects.Language

@Repository
internal class LexiconRepositoryAdapter(
  private val repository : JpaLexiconRepository
) : LexiconRepository {
  override fun findEntriesByPivotsAndLanguage(
    pivots: List<String>,
    lang: LexiconId
  ): List<LexicalEntry> {
    return emptyList()
  }

  override fun save(lexicon: Lexicon): Lexicon {
    val l = repository.save(LexiconEntity.fromDomain(lexicon))
    return l.toDomain()
  }

  override fun findByLanguage(language: Language): Lexicon? {
    TODO("Not yet implemented")
  }

  override fun existsByLanguage(language: Language): Boolean {
    return repository.existsByLang(language)
  }
}
