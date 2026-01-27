package sv.com.clip.dictionary.infrastructure.persistence.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import jakarta.persistence.Table
import org.springframework.data.domain.Persistable
import sv.com.clip.dictionary.domain.model.Form
import sv.com.clip.dictionary.domain.model.FormId
import sv.com.clip.dictionary.infrastructure.persistence.jpa.LexicalEntryEntity
import java.util.UUID

@Entity
@Table(name = "lexical_entry_forms")
class FormEntity(
    @Id
  private val id: UUID,
    @Column(name = "written_representation")
  val writtenRepresentation: String,
    val script: String?,
    @Column(name = "phonetic_ipa")
  val phoneticIPA: String?,
    @Column(name = "audio_url")
  val audioURL: String?,
    @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lexical_entry_id")
  var lexicalEntryEntity: LexicalEntryEntity? = null
) : Persistable<UUID> {
  fun toDomain() : Form {
    return Form(
      id = FormId(this.id),
      writtenRepresentation = this.writtenRepresentation,
      script = this.script,
      phoneticIPA = this.phoneticIPA,
      audioURL = this.audioURL,
    )
  }
  companion object {
    fun fromDomain(form: Form): FormEntity {
      return FormEntity(
        id = form.id.value,
        writtenRepresentation = form.writtenRepresentation,
        script = form.script,
        phoneticIPA = form.phoneticIPA,
        audioURL = form.audioURL,
      )
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
