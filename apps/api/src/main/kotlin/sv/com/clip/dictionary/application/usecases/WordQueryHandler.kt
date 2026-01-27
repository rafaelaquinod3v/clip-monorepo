package sv.com.clip.dictionary.application.usecases

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sv.com.clip.dictionary.web.SearchWordQuery
import sv.com.clip.dictionary.infrastructure.WordJdbcProjectionRepository
import sv.com.clip.dictionary.infrastructure.WordReadModel

@Service
class WordQueryHandler(private val repository: WordJdbcProjectionRepository) {

  @Transactional(readOnly = true)
  fun handle(query: SearchWordQuery): List<WordReadModel> {
    return repository.searchWord(query.term)
  }

}
