package sv.com.clip.learning.infrastructure.repository

import org.springframework.stereotype.Repository
import sv.com.clip.learning.domain.UserWord
import sv.com.clip.learning.domain.repository.UserWordRepository
import sv.com.clip.learning.infrastructure.jpa.UserWordEntity
import java.util.UUID

@Repository
class UserWordRepositoryAdapter(
  private val jpaUserWordRepository : JpaUserWordRepository
) : UserWordRepository {
  override fun findByUserIdAndTerm(userId: UUID, term: String): UserWord? {
    return jpaUserWordRepository.findByTerm(term)?.toDomain()
  }

  override fun findAllByTermIn(terms: Set<String>): List<UserWord> {
    return jpaUserWordRepository.findAllByTermIn(terms).map { UserWord(it.id, it.userId, it.term, it.status) }
  }

  override fun save(userWord: UserWord): UserWord {
    return jpaUserWordRepository.save(UserWordEntity.fromDomain(userWord)).toDomain()
  }

  override fun findAllByUserId(userId: UUID): List<UserWord> {
    TODO("Not yet implemented")
  }

  override fun deleteByUserIdAndTerm(userId: UUID, term: String) {
    jpaUserWordRepository.deleteByUserIdAndTerm(userId, term)
  }
}
