package sv.com.clip.learning.infrastructure.jpa

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "user_words")
class UserWordEntity(
  @Id
  val id: UUID,
  val term: String,
) {}
