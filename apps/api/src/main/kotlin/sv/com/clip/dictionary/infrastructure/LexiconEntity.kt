package sv.com.clip.dictionary.infrastructure

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import sv.com.clip.dictionary.domain.model.Lexicon
import sv.com.clip.dictionary.domain.model.LexiconId
import sv.com.clip.dictionary.domain.valueObjects.Language
import java.util.UUID

@Entity
@Table(name = "lexicons", uniqueConstraints = [UniqueConstraint(columnNames = ["lang"])])
class LexiconEntity(
  @Id
  val id: UUID,
  @Enumerated(EnumType.STRING)
  @Column(unique = true, nullable = false)
  val lang: Language,
) {
  fun toDomain(): Lexicon {
    return Lexicon(LexiconId(this.id), this.lang)
  }
  companion object {
    fun fromDomain(lexicon: Lexicon): LexiconEntity {
      return LexiconEntity(lexicon.id.uuid, lexicon.lang)
    }
  }
}
