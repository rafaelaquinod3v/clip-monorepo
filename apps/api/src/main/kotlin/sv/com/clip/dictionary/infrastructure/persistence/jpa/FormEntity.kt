package sv.com.clip.dictionary.infrastructure.persistence.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import sv.com.clip.dictionary.infrastructure.persistence.jpa.LexicalEntryEntity
import java.util.UUID

@Entity
@Table(name = "lexical_entry_forms")
class FormEntity(
    @Id
  val id: UUID,
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
) {}
