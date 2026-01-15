package sv.com.clip.vocabulary.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.vocabulary.application.ports.inbount.AddWordsCommand
import sv.com.clip.vocabulary.application.usecases.AddWordsCommandHandler
import sv.com.clip.vocabulary.domain.model.Word
import sv.com.clip.vocabulary.domain.repository.WordRepository
import sv.com.clip.vocabulary.domain.valueObject.LanguageLevel

@RestController
@RequestMapping("/vocabulary")
class WordsController(private val wordRepo: WordRepository, private val handler: AddWordsCommandHandler) {

  @GetMapping("/words")
  fun getWords(): List<Word>{
    return wordRepo.findAll()
  }

  @GetMapping("/words/languageLevel")
  fun getWordByLanguageLevel(@RequestParam(required = true, defaultValue = "A1") languageLevel: LanguageLevel): List<Word> {
    return wordRepo.findAllByLanguageLevel(languageLevel)
  }

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
        languageLevel = dto.languageLevel,
      )
    }
    handler.handle(AddWordsCommand(words))
    //println(words)
  }
}
