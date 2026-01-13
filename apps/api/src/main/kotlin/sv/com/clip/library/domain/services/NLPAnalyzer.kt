package sv.com.clip.library.domain.services

interface NLPAnalyzer {
  fun analyze(text: String): NLPAnalysisResult
}
