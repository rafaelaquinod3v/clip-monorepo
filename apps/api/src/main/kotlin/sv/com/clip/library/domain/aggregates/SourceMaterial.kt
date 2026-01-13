package sv.com.clip.library.domain.aggregates

import org.jmolecules.ddd.annotation.AggregateRoot
import java.time.LocalDateTime
import java.util.UUID

// SourceMaterial (Material de Origen): Es la entidad principal que representa el recurso bruto
// (video, audio, página web o libro).
// Debe rastrear su estado de procesamiento (ej. Pending, Processed, Analyzed).
@AggregateRoot
data class SourceMaterial(
  val id: UUID = UUID.randomUUID(),
  val title: String? = null,
  var rawText: String,
  val sourceUrl: String? = null,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  var transcript: Transcript? = null,
  var status: ProcessingStatus = ProcessingStatus.PENDING,
  var summary: String,
  var difficultyLevel: String? = null,
  ) {
  enum class ProcessingStatus {
    PENDING,
    PROCESSING,
    PROCESSED,
    ANALYZED,
    FAILED
  }
  data class Transcript(val rawText: String)

  fun updateTranscript(rawText: String) {
    // convertir rawText en transcript
    this.transcript = Transcript(rawText)
  }
  fun markAsAnalyzed(summary: String, difficultyLevel: String) {
    this.summary = summary
    this.difficultyLevel = difficultyLevel
    this.status = ProcessingStatus.ANALYZED
  }

  override fun toString(): String {
    return """
            [SourceMaterial ID: $id]
            ---------------------------------------
            Status: $status
            Title:  ${title ?: "Untitled"}
            Level:  ${difficultyLevel ?: "Unknown"}
            Summary: ${summary ?: "No summary available"}
            Content Preview: ${rawText.take(50).replace("\n", " ")}...
            Created: $createdAt
            ---------------------------------------
        """.trimIndent()
  }
}
