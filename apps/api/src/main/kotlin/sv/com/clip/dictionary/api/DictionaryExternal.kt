package sv.com.clip.dictionary.api

interface DictionaryExternal {
  fun getWords(words: Set<String>): List<WordDTO>
}
