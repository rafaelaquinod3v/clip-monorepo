package sv.com.clip.dictionary.domain.repository

import org.jmolecules.ddd.annotation.Repository
import sv.com.clip.dictionary.domain.model.LexicalEntry

@Repository
interface LexicalEntryRepository {
  fun save(lexicalEntry: LexicalEntry) : LexicalEntry
  fun findAll() : List<LexicalEntry> // TODO: pagination,
  fun saveAll(list: List<LexicalEntry>) : List<LexicalEntry>
}
