package sv.com.clip.dictionary.infrastructure.persistence.jpa

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import jakarta.persistence.Table
import org.springframework.data.domain.Persistable
import sv.com.clip.dictionary.domain.model.LexicalEntry
import sv.com.clip.dictionary.domain.model.LexicalEntryId
import sv.com.clip.dictionary.domain.model.LexiconId
import sv.com.clip.dictionary.domain.valueObjects.PartOfSpeech
import java.util.UUID

@Entity
@Table(name = "lexical_entries")
class LexicalEntryEntity(
    @Id
  @Column(name = "id")
  // Desempaquetamos el value class LexicalEntryId a UUID para la DB
  private val id: UUID,

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
) : Persistable<UUID> {

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
  fun toDomain(): LexicalEntry {
    val lexicalEntry = LexicalEntry(
      id = LexicalEntryId(this.id),
      sourceId = this.sourceId,
      lemma = this.lemma,
      lexiconId  = LexiconId(this.lexiconId),
      partOfSpeech = this.partOfSpeech,
      forms = this.forms.map { it.toDomain() },
      senses = this.senses.map { it.toDomain() },
    )

    return lexicalEntry
  }
  companion object {
    fun fromDomain(lexicalEntry: LexicalEntry): LexicalEntryEntity {
      val entity =  LexicalEntryEntity(
        id = lexicalEntry.id.value,
        sourceId = lexicalEntry.sourceId,
        lemma = lexicalEntry.lemma,
        lexiconId = lexicalEntry.lexiconId.uuid,
        partOfSpeech = lexicalEntry.partOfSpeech,
      )
      val formEntities = lexicalEntry.forms.map {
        form -> FormEntity.fromDomain(form).apply { this.lexicalEntryEntity = entity }
      }
      val senseEntities = lexicalEntry.senses.map {
        sense -> SenseEntity.fromDomain(sense).apply { this.lexicalEntryEntity = entity }
      }

      entity._forms.addAll(formEntities)
      entity._senses.addAll(senseEntities)

      return entity
    }
  }

  @Transient
  private var isNewEntity: Boolean = true

  // --- Implementación de Persistable ---

  override fun getId(): UUID = id

  override fun isNew(): Boolean = isNewEntity

  // Metodo para marcar que la entidad ya existe (usado al cargar de DB)
  @PostLoad
  @PostPersist
  fun markNotNew() {
    this.isNewEntity = false
  }
}
