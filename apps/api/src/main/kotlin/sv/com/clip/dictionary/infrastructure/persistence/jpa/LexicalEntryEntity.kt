package sv.com.clip.dictionary.infrastructure.persistence.jpa

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import sv.com.clip.dictionary.domain.valueObjects.PartOfSpeech
import sv.com.clip.dictionary.infrastructure.persistence.jpa.SenseEntity
import java.util.UUID

@Entity
@Table(name = "lexical_entries")
class LexicalEntryEntity(
    @Id
  @Column(name = "id")
  // Desempaquetamos el value class LexicalEntryId a UUID para la DB
  val id: UUID,

    val sourceId: String?,

    @Column(nullable = false)
  val lemma: String,

    @Column(name = "lexicon_id", nullable = false)
  val lexiconId: UUID, // Referencia al ID del lexicón (ej: "en", "es")

    @Enumerated(EnumType.STRING)
  @Column(name = "part_of_speech", nullable = false)
  val partOfSpeech: PartOfSpeech,

  // Relación con FormEntity (Hijas)
    @OneToMany(
    mappedBy = "lexicalEntryEntity",
    cascade = [CascadeType.ALL],
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  private val _forms: MutableList<FormEntity> = mutableListOf(),

  // Relación con SenseEntity (Hijas)
    @OneToMany(
    mappedBy = "lexicalEntryEntity",
    cascade = [CascadeType.ALL],
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  private val _senses: MutableList<SenseEntity> = mutableListOf()
) {
  val forms: List<FormEntity> get() = _forms.toList()
  val senses: List<SenseEntity> get() = _senses.toList()

  // Helpers para mantener la consistencia bidireccional
  fun addForm(form: FormEntity) {
    _forms.add(form)
    form.lexicalEntryEntity = this
  }

  fun addSense(sense: SenseEntity) {
    _senses.add(sense)
    sense.lexicalEntryEntity = this
  }
}
