package sv.com.clip.vocabulary.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.vocabulary.infrastructure.ImportVocabularyService

@RestController
@RequestMapping("/vocabulary")
class ImportVocabularyController(private val service: ImportVocabularyService) {

  @GetMapping("/import")
  fun importVocabulary(): Boolean {
    service.importVocabulary()
    return true
  }

  @GetMapping("/embedding")
  fun embeddingVocabulary(): Boolean {
    service.vocabularyAddEmbeddings()
    return true
  }
}
