package sv.com.clip.dictionary.infrastructure

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import sv.com.clip.dictionary.domain.model.Word
import sv.com.clip.dictionary.domain.model.WordIdentifier
import sv.com.clip.dictionary.domain.valueObjects.LanguageLevel
import sv.com.clip.dictionary.domain.valueObjects.PartOfSpeech
import java.util.UUID

@Entity
@Table(name = "words")
class WordEntity(
  @Id
  val id: UUID,
  @Column(nullable = false)
  var term: String,

  @Column(nullable = false)
  var lemma: String,

  @Enumerated(EnumType.STRING)
  var partOfSpeech: PartOfSpeech,

  @Enumerated(EnumType.STRING)
  var languageLevel: LanguageLevel,

  // El campo para los embeddings (384 dimensiones para MiniLM)
  // Definimos columnDefinition para que SQL sepa que es un 'vector'
  @Column(columnDefinition = "vector(384)")
  // JdbcTypeCode le dice a Hibernate que use el tipo VECTOR nativo de SQL
  @JdbcTypeCode(SqlTypes.VECTOR) // Requiere Hibernate 6.6+
  var embedding: FloatArray? = null
) {
  // Mapeo de Entidad -> Dominio
  fun toDomain(): Word = Word(
    id = WordIdentifier(this.id), // Envolvemos el UUID en el Value Object
    term = this.term,
    lemma = this.lemma,
    partOfSpeech = this.partOfSpeech,
    languageLevel = this.languageLevel
  )

  companion object {
    // Mapeo de Dominio -> Entidad
    fun fromDomain(word: Word): WordEntity = WordEntity(
      id = word.id.uuid, // Extraemos el valor del Value Object
      term = word.term,
      lemma = word.lemma,
      partOfSpeech = word.partOfSpeech,
      languageLevel = word.languageLevel
    )
  }
}
