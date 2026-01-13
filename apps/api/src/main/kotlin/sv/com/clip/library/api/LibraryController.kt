package sv.com.clip.library.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
//import sv.com.clip.vocabulary.application.LearningService


@RestController
@RequestMapping("/library")
// private val learningService: LearningService
class LibraryController() {

  @PostMapping("/import")
  fun addToLibrary(@RequestBody request: ImportRawTextSourceMaterialRequest): Boolean {
    println(request.rawText)
    return true
  }

  //@PostMapping("process")
  //suspend fun importNews(@RequestBody request: ImportRequest): ResponseEntity<ContentResponse> {
    //val result = learningService.processContent(request.url)
    //return ResponseEntity.ok(ContentResponse("result"))
  //}
}
