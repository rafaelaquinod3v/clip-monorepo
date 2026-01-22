package sv.com.clip.dictionary.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import sv.com.clip.dictionary.infrastructure.ImportVocabularyService
import java.io.File

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

  @PostMapping("/import-omw-json") // Open Multilingual WordNet
  fun importVocabularyOmwJson(@RequestParam("file") file: MultipartFile): Boolean {
    try {
      val fileName = file.originalFilename
      file.transferTo(File("/var/opt/clip/uploads/$fileName"))
      return true
    }catch (e: Exception) {
      return false
    }
  }
}
