package sv.com.clip.progression

import sv.com.clip.progression.domain.UserWord
import java.util.UUID

interface UserWordRepository {
  fun findByUserIdAndTerm(userId: UUID, term: String): UserWord?
  fun save(userWord: UserWord): UserWord
  fun findAllByUserId(userId: UUID): List<UserWord>
}
