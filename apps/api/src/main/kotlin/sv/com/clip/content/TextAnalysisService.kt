package sv.com.clip.content

interface TextAnalysisService {
  fun analyze(text: String): List<Token>
}
