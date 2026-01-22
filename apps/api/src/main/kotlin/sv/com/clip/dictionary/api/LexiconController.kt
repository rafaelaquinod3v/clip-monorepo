package sv.com.clip.dictionary.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.dictionary.application.LexiconApplicationService
import sv.com.clip.dictionary.domain.model.Lexicon
import sv.com.clip.dictionary.domain.repository.LexiconRepository
import sv.com.clip.dictionary.domain.services.LexiconCreator

@RestController
@RequestMapping("/lexicons")
class LexiconController(
  private val lexiconService: LexiconCreator,
) {

  @PostMapping
  fun createLexicon(@RequestBody lexicon: LexiconRequest): ResponseEntity<Lexicon> {
    val newLexicon = lexiconService.create(lexicon.lang)
    return ResponseEntity.ok(newLexicon)
  }
}
