package sv.com.clip.learning.application

import sv.com.clip.learning.domain.WordStatus

data class WordAnalysis(
  val word: String,        // El término original
  val definition: String? = null,  // Proveniente de Dictionary
  val status: WordStatus,  // Proveniente de Vocabulary (KNOWN, LEARNING, NEW)
  val lemma: String? = null // Opcional: la raíz de la palabra para mejor clasificación
)
