package sv.com.clip.dictionary.api

import org.springframework.ai.chat.client.ChatClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.dictionary.domain.model.Lexicon
import sv.com.clip.dictionary.domain.queries.LexiconProvider
import sv.com.clip.dictionary.domain.services.LexiconCreator

@RestController
@RequestMapping("/lexicons")
class LexiconController(
  private val lexiconService: LexiconCreator,
  private val lexiconProvider: LexiconProvider,
  private val chatClientBuilder: ChatClient.Builder
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

  private val chatClient = chatClientBuilder.build()
//
  @GetMapping("/translate")
  fun translate(@RequestParam("text") text: String): String? {
    return chatClient.prompt()
      .user("Traduce al inglés y dame un ejemplo: $text")
      .call()
      .content()
  }
}
