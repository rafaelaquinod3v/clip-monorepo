package sv.com.clip.dictionary.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import sv.com.clip.dictionary.domain.valueObject.LanguageLevel
import java.util.UUID

interface JpaWordRepository : JpaRepository<WordEntity, UUID> {
  fun findAllByLanguageLevel(language: LanguageLevel): List<WordEntity>
  // Búsqueda por similitud semántica
  @Query(value = """
        SELECT id, term, lemma, part_of_speech, language_level, NULL as embedding
        FROM words
        ORDER BY embedding <=> cast(:queryVector as vector)
        LIMIT :limit
    """, nativeQuery = true)
  fun findByTermSimilarity(
    @Param("queryVector") queryVector: String,
    @Param("limit") limit: Int = 5): List<WordEntity>
}
