package sv.com.clip.dictionary.infrastructure

import com.opencsv.bean.CsvBindByName

data class WordCSV(
  @CsvBindByName(column = "headword") val headword: String? = null,
  @CsvBindByName(column = "CEFR") val cefr: String? = null,
  @CsvBindByName(column = "lemma") val lemma: String? = null,
  @CsvBindByName(column = "pos") val pos: String? = null,
)
