package sv.com.clip.dictionary.infrastructure.persistence.jpa

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import sv.com.clip.dictionary.domain.model.UsageExample

@Embeddable
class UsageExampleEmbeddable(
    @Column(name = "text", nullable = false, columnDefinition = "TEXT") val text: String,
    @Column(name = "audio_url") val audioURL: String?,
) {
  fun toDomain(): UsageExample {
    return UsageExample(
      text = text,
      audioURL = audioURL,
    )
  }
  companion object {
    fun fromDomain(usageExample: UsageExample): UsageExampleEmbeddable {
      return UsageExampleEmbeddable(
        text = usageExample.text,
        audioURL = usageExample.audioURL,
      )
    }
  }
}
