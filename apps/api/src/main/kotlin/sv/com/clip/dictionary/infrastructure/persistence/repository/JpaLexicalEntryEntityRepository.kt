package sv.com.clip.dictionary.infrastructure.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import sv.com.clip.dictionary.api.WordDTO
import sv.com.clip.dictionary.infrastructure.persistence.jpa.LexicalEntryEntity
import java.util.UUID

interface JpaLexicalEntryEntityRepository : JpaRepository<LexicalEntryEntity, UUID> {
  fun findAllByLemmaIn(lemmas: Set<String>) : List<LexicalEntryEntity>

  @Query("SELECT DISTINCT l FROM LexicalEntryEntity l LEFT JOIN FETCH l._senses WHERE l.lemma IN :lemmas")
  fun findAllByLemmaInWithSenses(@Param("lemmas") lemmas: Set<String>): Set<LexicalEntryEntity>

  @Query("""
        SELECT DISTINCT l FROM LexicalEntryEntity l
        LEFT JOIN FETCH l._senses
        LEFT JOIN FETCH l._forms
        WHERE l.lemma IN :lemmas
  """)
  fun findAllByLemmaInWithDetails(@Param("lemmas") lemmas: Set<String>): Set<LexicalEntryEntity>


  @Query("""
        SELECT new sv.com.clip.dictionary.api.WordDTO(l.id, l.lemma, s.definition)
        FROM LexicalEntryEntity l
        JOIN l._senses s
        WHERE l.lemma IN :lemmas
    """)
  fun findProjectionsByLemmaIn(@Param("lemmas") lemmas: Set<String>): List<WordDTO>
}
