package sv.com.clip.learning.application

data class AnalysisSummary(
  val totalWords: Int,
  val uniqueWords: Int,
  val knownCount: Int,
  val learningCount: Int,
  val unknownCount: Int,
  val percentageKnown: Double
)
