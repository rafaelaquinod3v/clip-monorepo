package sv.com.clip.dictionary.infrastructure

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import sv.com.clip.dictionary.infrastructure.WordReadModel
import java.util.UUID

@Repository
class WordJdbcProjectionRepository(private val jdbcClient: JdbcClient) {
  /**
   * Busca palabras en la proyección optimizada.
   * El mapeo a WordReadModel es automático si los nombres de
   * las columnas coinciden con las propiedades de la Data Class.
   */
  fun searchWord(term: String): List<WordReadModel> {
    val results = jdbcClient.sql("""
      SELECT id, term, lemma, spanish_translation
      FROM word_search_view
      WHERE term ILIKE :searchTerm
    """.trimIndent())
      .param("searchTerm", "%$term%")
      .query(WordReadModel::class.java)
      .list()
    return results?.filterNotNull() ?: emptyList()
  }

  /**
   * Ejemplo de búsqueda por ID para obtener el detalle completo
   */
  fun findById(id: UUID): WordReadModel? {
    return jdbcClient.sql("SELECT * FROM word_search_view WHERE id = :id")
      .param("id", id)
      .query(WordReadModel::class.java)
      .optional()
      .orElse(null)
  }
}
