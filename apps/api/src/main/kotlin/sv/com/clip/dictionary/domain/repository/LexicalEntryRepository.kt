package sv.com.clip.dictionary.domain.repository

import org.jmolecules.ddd.annotation.Repository
import sv.com.clip.dictionary.api.WordDTO
import sv.com.clip.dictionary.domain.model.LexicalEntry

@Repository
interface LexicalEntryRepository {
  fun save(lexicalEntry: LexicalEntry) : LexicalEntry
  fun findAll() : List<LexicalEntry> // TODO: pagination,
  fun findAllByLemmaIn(lemmas: Set<String>) : List<LexicalEntry>
  fun findAllByLemmaInWithDetails(lemma: Set<String>) : List<LexicalEntry>
  fun findProjectionsByLemmaIn(lemmas: Set<String>) : List<WordDTO>
  fun saveAll(list: List<LexicalEntry>) : List<LexicalEntry>
}
