package sv.com.clip.learning.domain

enum class WordStatus(val code: Int) {
  NOT_FOUND(-1),
  IGNORED(0),
  NEW(1),
  RECOGNIZED(2),
  FAMILIAR(3),
  LEARNED(4),
  KNOWN(5);
  companion object {
    fun fromCode(code: Int?) = entries.find { it.code == code } ?: RECOGNIZED
  }
}
