package sv.com.clip.content

interface ContentProcessor {
  // Servicio de dominio para procesar multimedia (IA o NLP)
  suspend fun processMultimedia(sourceUrl: String): String
}
