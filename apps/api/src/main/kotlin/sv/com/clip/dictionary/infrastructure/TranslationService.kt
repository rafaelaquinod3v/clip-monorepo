package sv.com.clip.dictionary.infrastructure

import kotlinx.coroutines.runBlocking
import me.bush.translator.Language
import me.bush.translator.Translator
import org.springframework.stereotype.Service

// Spring AI (OpenAI/Claude)
// DeepL
@Service
class TranslationService(private val translator: Translator) {
  fun translate(text: String): String = runBlocking {
      return@runBlocking try {
          val result = translator.translate(
              text = text,
              source = Language.ENGLISH,
              target = Language.SPANISH
          )
          println(result)
          result.translatedText
      } catch (e: Exception) {
          "Error: ${e.message}"

      }
  }
}
