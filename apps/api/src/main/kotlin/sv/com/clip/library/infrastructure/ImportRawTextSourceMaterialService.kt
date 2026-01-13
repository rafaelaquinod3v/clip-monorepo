package sv.com.clip.library.infrastructure

import org.springframework.stereotype.Service
import sv.com.clip.library.domain.aggregates.SourceMaterial
//import sv.com.clip.library.domain.repository.SourceMaterialRepository
import sv.com.clip.library.domain.services.ImportRawTextSourceMaterial
import sv.com.clip.library.domain.services.NLPAnalyzer

@Service
class ImportRawTextSourceMaterialService(
  private val nlp: NLPAnalyzer,

): ImportRawTextSourceMaterial {
  // private val repo: SourceMaterialRepository
  override fun import(rawText: String): SourceMaterial {
    // Analisis NLP simulado
    val analysis = nlp.analyze(rawText)

    // entidad de dominio
    val source = SourceMaterial(
      title = "raw",
      rawText = rawText,
      summary = analysis.summary
    )

    //repo.save(source)
    return source
  }
}
