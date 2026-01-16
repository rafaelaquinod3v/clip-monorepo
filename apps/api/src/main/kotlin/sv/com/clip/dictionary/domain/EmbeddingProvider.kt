package sv.com.clip.dictionary.domain

interface EmbeddingProvider {
  fun calculate(term: String): FloatArray
}
