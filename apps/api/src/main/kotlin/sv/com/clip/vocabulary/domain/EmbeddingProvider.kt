package sv.com.clip.vocabulary.domain

interface EmbeddingProvider {
  fun calculate(term: String): FloatArray
}
