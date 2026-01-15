package sv.com.clip.vocabulary.infrastructure

import ai.djl.repository.zoo.Criteria
import ai.djl.repository.zoo.ZooModel
import jakarta.annotation.PreDestroy
import org.springframework.stereotype.Service
import sv.com.clip.vocabulary.domain.EmbeddingProvider

@Service
class DjlEmbeddingAdapter : EmbeddingProvider {
  private val model: ZooModel<String, FloatArray> = Criteria.builder()
    .setTypes(String::class.java, FloatArray::class.java)
    .optModelUrls("djl://ai.djl.huggingface.pytorch/sentence-transformers/all-MiniLM-L6-v2")
    .optEngine("PyTorch")
    .build()
    .loadModel()

  override fun calculate(term: String): FloatArray {
    model.newPredictor().use { predictor ->
      return predictor.predict(term)
    }
  }

  @PreDestroy
  fun close() = model.close()
}
