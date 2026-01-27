package sv.com.clip.learning.infrastructure.repository

import org.springframework.stereotype.Repository
import sv.com.clip.learning.domain.UserWord
import sv.com.clip.learning.domain.repository.UserWordRepository
import java.util.UUID

@Repository
class UserWordRepositoryAdapter(
  private val jpaUserWordRepository : JpaUserWordRepository
) : UserWordRepository {
  override fun findByUserIdAndTerm(userId: UUID, term: String): UserWord? {
    TODO("Not yet implemented")
  }

  override fun findAllByTermIn(terms: Set<String>): List<UserWord> {
    return jpaUserWordRepository.findAllByTermIn(terms).map { UserWord(it.id, term = it.term) }
  }

  override fun save(userWord: UserWord): UserWord {
    TODO("Not yet implemented")
  }

  override fun findAllByUserId(userId: UUID): List<UserWord> {
    TODO("Not yet implemented")
  }
}
