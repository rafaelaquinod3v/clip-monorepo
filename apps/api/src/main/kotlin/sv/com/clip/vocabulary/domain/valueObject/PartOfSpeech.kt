package sv.com.clip.vocabulary.domain.valueObject

// Universal Dependencies (UD)
enum class PartOfSpeech(val description: String) {
  NOUN("Noun"),
  VERB("Verb"),
  ADJ("Adjective"),
  ADV("Adverb"),
  PRON("Pronoun"),
  ADP("Preposition"),
  CCONJ("Conjunction"),
  INTJ("Interjection"),
}
