package sv.com.clip.dictionary.domain.gateways

import sv.com.clip.dictionary.domain.valueObjects.Language

interface TranslationGateway {
  fun translate(text: String, sourceLang: Language, targetLang: Language): String?
}
