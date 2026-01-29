package sv.com.clip.learning.application

data class AnalysisResult(
  val words: List<WordAnalysis>,
  val rawText: String? = null,
  val summary: AnalysisSummary? = null,
)
