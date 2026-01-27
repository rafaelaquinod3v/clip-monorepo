package sv.com.clip.dictionary.internal

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sv.com.clip.dictionary.api.DictionaryExternal
import sv.com.clip.dictionary.api.WordDTO
import sv.com.clip.dictionary.domain.repository.LexicalEntryRepository
import java.util.UUID

@Service
internal class DictionaryService(
  private val lexicalEntryRepository: LexicalEntryRepository
) : DictionaryExternal{

  @Transactional(readOnly = true)
  override fun getWords(words: Set<String>): List<WordDTO> {

    val entities = lexicalEntryRepository.findProjectionsByLemmaIn(words)
  return entities
    .groupBy { it.word } // Agrupar por el texto del lemma
    .map { (word, variations) ->
      val firstEntity = variations.first()
      WordDTO(
        id = firstEntity.id,
        // Unimos todos los sentidos únicos de todas las variaciones encontradas
        definition = variations.mapNotNull { it.definition }
          .distinct()
          .joinToString("; "),
        word = word,
      )
    }
}

}
