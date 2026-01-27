package sv.com.clip.learning.domain

enum class WordStatus(code: Int) {
  NOT_FOUND(-1),
  IGNORED(0),
  NEW(1),
  RECOGNIZED(2),
  FAMILIAR(3),
  LEARNED(4),
  KNOWN(5),
}
