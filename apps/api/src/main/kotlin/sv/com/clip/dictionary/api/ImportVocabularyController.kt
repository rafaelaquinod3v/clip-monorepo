package sv.com.clip.dictionary.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.dictionary.infrastructure.ImportVocabularyService

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
