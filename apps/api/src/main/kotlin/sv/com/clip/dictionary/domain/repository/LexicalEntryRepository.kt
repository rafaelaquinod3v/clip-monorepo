package sv.com.clip.dictionary.domain.repository

import sv.com.clip.dictionary.domain.model.LexicalEntry

interface LexicalEntryRepository {
  fun save(lexicon: LexicalEntry) : LexicalEntry
  fun findAll() : List<LexicalEntry> // TODO: pagination,
}
