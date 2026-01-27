package sv.com.clip.learning.application

data class AnalysisResult(
  val words: List<WordAnalysis>,
  val summary: AnalysisSummary? = null,
)
