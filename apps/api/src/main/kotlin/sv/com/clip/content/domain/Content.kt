package sv.com.clip.content.domain

import sv.com.clip.content.ContentType
import sv.com.clip.content.TextAnalysisService
import sv.com.clip.content.Token
import java.util.UUID

class Content(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val rawText: String,
    val contentType: ContentType,
    val language: String = "en",
    val tokens: MutableList<Token> = mutableListOf()
) {

  fun processTokens(analyzer: TextAnalysisService) {
    val analyzedWords = analyzer.analyze(this.rawText)
    this.tokens.clear()
    this.tokens.addAll(analyzedWords)
  }

  fun getUniqueLemmas(): Set<String> = tokens.map { it.lemma }.toSet()
    fun tokenize(): List<String> = rawText.split(Regex("\\W+")).filter { it.isNotBlank() }
}
