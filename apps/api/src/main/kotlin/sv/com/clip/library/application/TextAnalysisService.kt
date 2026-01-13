package sv.com.clip.library.application

import sv.com.clip.library.domain.MediaToken

interface TextAnalysisService {
  fun analyze(text: String): List<MediaToken>
}
