package sv.com.clip.dictionary.infrastructure.gateways

import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.stereotype.Component
import sv.com.clip.dictionary.domain.gateways.TranslationGateway
import sv.com.clip.dictionary.domain.model.LexicalEntry
import sv.com.clip.dictionary.domain.valueObjects.Language

@Component
class OllamaTranslationGateway(
  private val chatModel: ChatModel
) : TranslationGateway {
//  override fun translate(
//    text: String,
//    sourceLang: Language,
//    targetLang: Language
//  ): String? {
//    val message = """
//      Traduce la siguiente palabra o frase del ${sourceLang.value} al ${targetLang.value}.
//      Texto: "$text"
//      Responde únicamente con la traducción técnica y directa.
//    """.trimIndent()
    // Llamada al modelo local Gemma2:2b
//    val response = chatModel.call(Prompt(message))
//
//    return response.result.output.text?.trim()
//  }

//  override fun translateLexicalEntry(
//    lexicalEntry: LexicalEntry,
//    sourceLang: Language,
//    targetLang: Language
//  ): LexicalEntry? {
//    TODO("Not yet implemented")
//  }

  override fun translate(text: String, sourceLang: Language, targetLang: Language): String? {
    // Prompt optimizado para evitar "alucinaciones" y explicaciones innecesarias
    val promptText = """
            Act as a professional lexicographer.
            Translate the following term from ${sourceLang.value} to ${targetLang.value}.
            Term: "$text"
            Return ONLY the translated string, no explanations, no punctuation unless necessary.
        """.trimIndent()

    return chatModel.call(promptText).trim()
  }

  override fun translateLexicalEntry(
    lexicalEntry: LexicalEntry,
    sourceLang: Language,
    targetLang: Language
  ): LexicalEntry? {
    // En 2026, usamos Structured Output para recibir un JSON exacto
    val promptText = """
            Translate this dictionary entry from ${sourceLang.value} to ${targetLang.value}.
            Lemma: ${lexicalEntry.lemma}
            Part of Speech: ${lexicalEntry.partOfSpeech}
            Senses:
            ${lexicalEntry.senses.joinToString("\n") { "- Definition: ${it.definition}" }}

            Return a JSON object with the following structure:
            {
              "lemma": "translated lemma",
              "senses": [
                { "definition": "translated definition" }
              ]
            }
        """.trimIndent()

    return try {
      val response = chatModel.call(promptText)
      // Aquí deberías usar tu ObjectMapper para convertir la respuesta JSON a LexicalEntry
      return null
//      parseJsonToLexicalEntry(response, lexicalEntry, targetLang)
    } catch (e: Exception) {
      null // O manejar el reintento
    }
  }

  override fun translateToSpanish(lexicalEntry: LexicalEntry): LexicalEntry? {
    TODO("Not yet implemented")
  }

  override fun translateToEnglish(lexicalEntry: LexicalEntry): LexicalEntry? {
    TODO("Not yet implemented")
  }

}
