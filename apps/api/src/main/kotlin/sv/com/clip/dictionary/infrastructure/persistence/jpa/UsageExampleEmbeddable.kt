package sv.com.clip.dictionary.infrastructure.persistence.jpa

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class UsageExampleEmbeddable(
    @Column(name = "text", nullable = false, columnDefinition = "TEXT") val text: String,
    @Column(name = "audio_url") val audioURL: String?,
)
