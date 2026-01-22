package sv.com.clip.dictionary.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.dictionary.application.ports.inbount.AddWordsCommand
import sv.com.clip.dictionary.application.usecases.AddWordsCommandHandler
import sv.com.clip.dictionary.application.usecases.WordQueryHandler
import sv.com.clip.dictionary.domain.model.Word
import sv.com.clip.dictionary.domain.repository.WordRepository
import sv.com.clip.dictionary.domain.valueObjects.CEFRLevel
import sv.com.clip.dictionary.infrastructure.WordReadModel

@RestController
@RequestMapping("/vocabulary")
class WordsController(private val queryHandler: WordQueryHandler, private val wordRepo: WordRepository, private val handler: AddWordsCommandHandler) {

  @GetMapping("/words")
  fun getWords(): List<Word>{
    return wordRepo.findAll()
  }

  //@GetMapping("/words/languageLevel")
  //fun getWordByLanguageLevel(@RequestParam(required = true, defaultValue = "A1") CEFRLevel: CEFRLevel): List<Word> {
    ///return wordRepo.findAllByLanguageLevel(CEFRLevel)
  //}

  @GetMapping("/words/similarity")
  fun getWordSimilarity(@RequestParam(required = true, defaultValue = "run") term: String): List<Word> {
    return wordRepo.findByTermSimilarity(term)
  }

  @PostMapping("/words/bulk")
  fun addWordWords(@RequestBody request: List<WordRequest>){
    val words = request.map { dto ->
      Word(
        term = dto.term,
        lemma = dto.lemma,
        partOfSpeech = dto.partOfSpeech,
        //cefrLevel = dto.CEFRLevel,
      )
    }
    handler.handle(AddWordsCommand(words))
  }

  @GetMapping("/words/search")
  fun search(@RequestParam(required = true, defaultValue = "hello") term: String): List<WordReadModel> {
    return queryHandler.handle(SearchWordQuery(term))
  }
}
