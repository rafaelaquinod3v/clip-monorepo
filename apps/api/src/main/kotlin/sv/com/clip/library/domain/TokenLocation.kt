package sv.com.clip.library.domain

data class TokenLocation (val contentType: ContentType, val coordinates: Map<String, String>) {
  fun getTimeStamp(): Long? {
    return coordinates["timestamp"]?.toLongOrNull()
  }
}
