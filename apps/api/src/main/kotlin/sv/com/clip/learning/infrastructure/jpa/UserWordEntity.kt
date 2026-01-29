package sv.com.clip.learning.infrastructure.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import sv.com.clip.learning.domain.UserWord
import sv.com.clip.learning.domain.WordStatus
import java.util.UUID

@Entity
@Table(name = "user_words")
class UserWordEntity(
  @Id
  val id: UUID,
  val userId: UUID,
  val term: String,
  @Enumerated(EnumType.STRING)
  @Column(name = "status", length = 20)
  val status: WordStatus,
) {
  fun toDomain() : UserWord {
    return UserWord(this.id, this.userId, this.term, this.status)
  }
  companion object {
    fun fromDomain(userWord : UserWord) : UserWordEntity {
      return UserWordEntity(userWord.id, userWord.userId, userWord.term, userWord.status)
    }
  }
}
