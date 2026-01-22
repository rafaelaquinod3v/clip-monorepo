package sv.com.clip.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class JacksonConfig {
  @Bean
  @Primary // Asegura que Spring use esta instancia por defecto
  fun objectMapper(): ObjectMapper {
    return ObjectMapper()
      .findAndRegisterModules() // Soporta tipos modernos como Java 8 Dates
  }
}
