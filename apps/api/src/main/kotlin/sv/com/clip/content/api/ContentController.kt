package sv.com.clip.content.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.content.LearningService
import sv.com.clip.content.dto.ContentResponse
import sv.com.clip.content.dto.ImportRequest

@RestController
@RequestMapping("/contents")
class ContentController(private val learningService: LearningService) {
  @PostMapping("process")
  suspend fun importNews(@RequestBody request: ImportRequest): ResponseEntity<ContentResponse> {
    val result = learningService.processContent(request.url)
    return ResponseEntity.ok(ContentResponse(result))
  }
}
