package sv.com.clip.dictionary.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.dictionary.application.services.TranslationService
import sv.com.clip.dictionary.domain.model.Lexicon
import sv.com.clip.dictionary.domain.queries.LexiconProvider
import sv.com.clip.dictionary.domain.services.LexiconCreator
import sv.com.clip.dictionary.domain.valueObjects.Language

@RestController
@RequestMapping("/lexicons")
class LexiconController(
  private val lexiconService: LexiconCreator,
  private val lexiconProvider: LexiconProvider,
  private val translationService: TranslationService,
) {

  @PostMapping
  fun createLexicon(@RequestBody lexicon: LexiconRequest): ResponseEntity<Lexicon> {
    val newLexicon = lexiconService.create(lexicon.lang)
    return ResponseEntity.ok(newLexicon)
  }

  @GetMapping
  fun findAll() : List<Lexicon> {
    return lexiconProvider.findAll()
  }

  @GetMapping("/translate")
  fun translate(
    @RequestParam("text") text: String,
    @RequestParam("sourceLang") sourceLang: Language,
    @RequestParam("targetLang") targetLang: Language,
  ): String? {
    return translationService.translate(text, sourceLang, targetLang)
  }
}
