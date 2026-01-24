package sv.com.clip.dictionary.infrastructure.gateways

import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.stereotype.Component
import sv.com.clip.dictionary.domain.gateways.TranslationGateway
import sv.com.clip.dictionary.domain.valueObjects.Language

@Component
class OllamaTranslationGateway(
  private val chatModel: ChatModel
) : TranslationGateway {
  override fun translate(
    text: String,
    sourceLang: Language,
    targetLang: Language
  ): String? {
    val message = """
      Traduce la siguiente palabra o frase del ${sourceLang.value} al ${targetLang.value}.
      Texto: "$text"
      Responde únicamente con la traducción técnica y directa.
    """.trimIndent()
    // Llamada al modelo local Gemma2:2b
    val response = chatModel.call(Prompt(message))

    return response.result.output.text?.trim()
  }

}
