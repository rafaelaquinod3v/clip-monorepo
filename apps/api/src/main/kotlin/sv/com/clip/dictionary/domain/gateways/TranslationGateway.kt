package sv.com.clip.dictionary.domain.gateways

import sv.com.clip.dictionary.domain.model.LexicalEntry
import sv.com.clip.dictionary.domain.valueObjects.Language

interface TranslationGateway {
  fun translate(text: String, sourceLang: Language, targetLang: Language): String?
  fun translateLexicalEntry(lexicalEntry: LexicalEntry, sourceLang: Language, targetLang: Language): LexicalEntry?
  fun translateToSpanish(lexicalEntry: LexicalEntry): LexicalEntry?
  fun translateToEnglish(lexicalEntry: LexicalEntry): LexicalEntry?
}
