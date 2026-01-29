package sv.com.clip.learning.infrastructure.repository

import org.springframework.data.jpa.repository.JpaRepository
import sv.com.clip.learning.infrastructure.jpa.UserWordEntity
import java.util.UUID

interface JpaUserWordRepository : JpaRepository<UserWordEntity, UUID> {
  fun findAllByTermIn(terms: Set<String>) : List<UserWordEntity>
  fun deleteByUserIdAndTerm(userId: UUID, term: String)
  fun findByUserIdAndTerm(userId: UUID, term: String)
  fun findByTerm(term: String): UserWordEntity?
}
