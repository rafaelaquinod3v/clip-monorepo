package sv.com.clip.dictionary.infrastructure.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import sv.com.clip.dictionary.domain.valueObjects.Language
import sv.com.clip.dictionary.infrastructure.persistence.jpa.LexiconEntity
import java.util.UUID

interface JpaLexiconRepository : JpaRepository<LexiconEntity, UUID> {
  fun existsByLang(lang: Language): Boolean
  fun findByLang(lang: Language): LexiconEntity?
}
