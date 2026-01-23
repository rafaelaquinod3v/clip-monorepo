package sv.com.clip.dictionary.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sv.com.clip.dictionary.domain.repository.LexiconRepository
import sv.com.clip.dictionary.domain.services.LexiconCreator
import sv.com.clip.dictionary.domain.services.LexiconRegistry

@Configuration
internal class DictionaryConfig {
  @Bean
  fun lexiconCreator(repository: LexiconRepository) : LexiconCreator {
    return LexiconRegistry(repository)
  }
}
