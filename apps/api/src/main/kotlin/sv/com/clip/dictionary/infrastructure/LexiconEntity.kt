package sv.com.clip.dictionary.infrastructure

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.springframework.data.domain.Persistable
import sv.com.clip.dictionary.domain.model.Lexicon
import sv.com.clip.dictionary.domain.model.LexiconId
import sv.com.clip.dictionary.domain.valueObjects.Language
import java.util.UUID

@Entity
@Table(name = "lexicons", uniqueConstraints = [UniqueConstraint(columnNames = ["lang"])])
class LexiconEntity(
  @Id
  @Column(columnDefinition = "UUID")
  private val id: UUID,
  @Enumerated(EnumType.STRING)
  @Column(unique = true, nullable = false)
  val lang: Language,
) : Persistable<UUID> {
  override fun getId(): UUID = id
  // Campo transitorio para controlar si es nuevo o no
  @Transient
  private var isNewRecord: Boolean = true
  // Hibernate llamará a esto para decidir entre persist() o merge()
  override fun isNew(): Boolean = isNewRecord
  // Metodo de conveniencia para cuando cargamos de la BD
  fun markNotNew() {
    this.isNewRecord = false
  }
  // Callback de JPA que se ejecuta después de cargar de la BD
  @jakarta.persistence.PostLoad
  @jakarta.persistence.PostPersist
  fun markNotNewAfterLoad() {
    this.isNewRecord = false
  }
  fun toDomain(): Lexicon {
    return Lexicon(LexiconId(this.id), this.lang)
  }
  companion object {
    fun fromDomain(lexicon: Lexicon): LexiconEntity {
      return LexiconEntity(lexicon.id.uuid, lexicon.lang)
    }
  }
}
