package sv.com.clip.learning.domain.repository

import org.jmolecules.ddd.annotation.Repository
import sv.com.clip.learning.domain.UserWord
import java.util.UUID

@Repository
interface UserWordRepository {
  fun findByUserIdAndTerm(userId: UUID, term: String): UserWord?
  fun findAllByTermIn(terms: Set<String>) : List<UserWord>
  fun save(userWord: UserWord): UserWord
  fun findAllByUserId(userId: UUID): List<UserWord>
}
