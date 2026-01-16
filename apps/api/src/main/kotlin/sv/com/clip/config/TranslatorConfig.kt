package sv.com.clip.config

import me.bush.translator.Translator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TranslatorConfig {
  @Bean
  fun googleTranslator(): Translator {
    return Translator()
  }
}
