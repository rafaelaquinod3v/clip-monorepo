package sv.com.clip.dictionary.infrastructure.readmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.modulith.events.ApplicationModuleListener
import org.springframework.stereotype.Component
import sv.com.clip.dictionary.infrastructure.TranslatorService
import sv.com.clip.dictionary.domain.events.WordsAddedEvent
import sv.com.clip.dictionary.domain.repository.WordRepository

@Component
class WordReadModelProjector(val traslator: TranslatorService, val repo: WordRepository, private val jdbcClient: JdbcClient) {

  @ApplicationModuleListener
  fun on(event: WordsAddedEvent) = runBlocking {
    println("--- Word read model projector ---")
    val words = repo.findAllById(event.wordIds)
    val jobs = words.map { word ->
      async(Dispatchers.IO) {
        jdbcClient.sql("""
                INSERT INTO word_search_view (id, term, lemma, spanish_translation)
                VALUES (:id, :term, :lemma, :spanish_translation)
                ON CONFLICT (id) DO UPDATE SET term = EXCLUDED.term
            """)
          .param("id", word.id.uuid)
          .param("term", word.term)
          .param("lemma", word.lemma)
          .param("spanish_translation", traslator.translate(word.term))
          .update()
      }
    }
    jobs.awaitAll()
    println("Proyección actualizada para ${event.wordIds.size} palabras")
  }
}
