package sv.com.clip.dictionary.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import sv.com.clip.dictionary.application.services.ImportVocabularyService
import sv.com.clip.dictionary.domain.valueObjects.Language
import java.io.File

//import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vocabulary")
class ImportVocabularyController(
  private val service: ImportVocabularyService,
  ) {

  @GetMapping("/import")
  fun importVocabulary(): Boolean {
    //service.importVocabulary()
    return true
  }

  @GetMapping("/embedding")
  fun embeddingVocabulary(): Boolean {
    //service.vocabularyAddEmbeddings()
    return true
  }

  @PostMapping("/import-omw-json") // Open Multilingual WordNet
  fun importVocabularyOmwJson(
    @RequestParam("file") file: MultipartFile,
    @RequestParam("lang") lang: Language,
  ): Boolean {
    try {
      val fileName = file.originalFilename ?: "unknown_${System.currentTimeMillis()}"
      val destFile = File("/var/opt/clip/uploads/$fileName")
      file.transferTo(destFile)
      service.startAsyncImport(destFile, lang)
      return true
    }catch (e: Exception) {
      e.printStackTrace()
      return false
    }
  }
}
