package sv.com.clip.library.domain

interface ContentProcessor {
  // Servicio de dominio para procesar multimedia (IA o NLP)
  suspend fun processMultimedia(sourceUrl: String): String
}
