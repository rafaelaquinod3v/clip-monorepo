package sv.com.clip.library.domain

import sv.com.clip.library.application.TextAnalysisService
import java.util.UUID

class Content(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val rawText: String,
    val contentType: ContentType,
    val language: String = "en",
    val tokens: MutableList<MediaToken> = mutableListOf()
) {

  fun processTokens(analyzer: TextAnalysisService) {
    val analyzedWords = analyzer.analyze(this.rawText)
    this.tokens.clear()
    this.tokens.addAll(analyzedWords)
  }

  //fun getUniqueLemmas(): Set<String> = tokens.map { it.rawText }.toSet()
  fun tokenize(): List<String> = rawText.split(Regex("\\W+")).filter { it.isNotBlank() }
}
