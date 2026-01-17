package sv.com.clip.dictionary.domain.valueObjects

// Universal Dependencies (UD)
enum class PartOfSpeech(val description: String) {
  NOUN("Noun"),
  VERB("Verb"),
  ADJ("Adjective"),
  ADV("Adverb"),
  PRON("Pronoun"),
  PROPN("Proper Noun"),
  SCONJ("Subordinating Conjunction"),
  AUX("Auxiliary Verbs"),
  NUM("Number"),
  X("Words that for some reason cannot be assigned a real part-of-speech"),
  DET("Pronominal Quantifiers"),
  PART("Particles"),
  ADP("Preposition"),
  CCONJ("Conjunction"),
  INTJ("Interjection"),
}
