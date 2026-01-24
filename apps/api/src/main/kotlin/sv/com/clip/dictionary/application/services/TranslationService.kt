package sv.com.clip.dictionary.application.services

import org.springframework.stereotype.Service
import sv.com.clip.dictionary.domain.gateways.TranslationGateway
import sv.com.clip.dictionary.domain.valueObjects.Language

@Service
class TranslationService(
  private val translationGateway: TranslationGateway
) {
  fun translate(text: String, sourceLang: Language, targetLang: Language): String? {
    return translationGateway.translate(text, sourceLang, targetLang)
  }
}
