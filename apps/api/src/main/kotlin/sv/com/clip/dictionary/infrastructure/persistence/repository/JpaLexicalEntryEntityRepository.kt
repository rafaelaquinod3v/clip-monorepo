package sv.com.clip.dictionary.infrastructure.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import sv.com.clip.dictionary.infrastructure.persistence.jpa.LexicalEntryEntity
import java.util.UUID

interface JpaLexicalEntryEntityRepository : JpaRepository<LexicalEntryEntity, UUID> {}
