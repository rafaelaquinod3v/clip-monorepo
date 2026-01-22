package sv.com.clip.dictionary.application

import org.springframework.stereotype.Service
import sv.com.clip.dictionary.domain.model.LanguageId
import sv.com.clip.dictionary.domain.model.LexicalEntry
import sv.com.clip.dictionary.domain.repository.LexiconRepository

@Service
class TranslationService(private val allLexiconsRepository: LexiconRepository) {

  fun translate(sourceEntry: LexicalEntry, targetLanguage: LanguageId): List<LexicalEntry> {
    val pivotIds = sourceEntry.senses.map { it.conceptId.ili }.toList()
    return allLexiconsRepository.findEntriesByPivotsAndLanguage(pivotIds, targetLanguage)
//    return allLexicons.filter { entry ->
//        entry.languageId.value == targetLanguage.value &&

//        entry.senses.any { sense -> sense.conceptId.iliId.value in pivotIds }
//    }

  }
}
