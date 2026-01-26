package sv.com.clip.dictionary.domain.repository

import org.jmolecules.ddd.annotation.Repository
import sv.com.clip.dictionary.domain.model.LexicalEntry

@Repository
interface LexicalEntryRepository {
  fun save(lexicon: LexicalEntry) : LexicalEntry
  fun findAll() : List<LexicalEntry> // TODO: pagination,
}
