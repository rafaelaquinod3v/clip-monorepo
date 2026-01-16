package sv.com.clip.dictionary.infrastructure

import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.stereotype.Service
import sv.com.clip.dictionary.domain.model.Word
import sv.com.clip.dictionary.domain.repository.WordRepository
import sv.com.clip.dictionary.domain.valueObject.LanguageLevel
import sv.com.clip.dictionary.domain.valueObject.PartOfSpeech
import java.io.FileReader

@Service
class ImportVocabularyService(private val wordRepository: WordRepository, private val embedding: DjlEmbeddingAdapter) {
  val RUTA_ARCHIVO: String = "/home/rafae/code/english-learning/clip-monorepo/python_utils/ENGLISH_CERF_WORDS_LEMMA_POS.csv"
  fun obtenerLista(): List<WordCSV> {
    return try {
      FileReader(RUTA_ARCHIVO).use { reader ->
        CsvToBeanBuilder<WordCSV>(reader)
          .withType(WordCSV::class.java)
          .withIgnoreLeadingWhiteSpace(true)
          .build()
          .parse()
      }
    }catch (e: Exception) {
      throw RuntimeException("Error al leer el csv: ${e.message}")
    }
  }

  fun importVocabulary() {
    val lista = obtenerLista()
    lista.forEach { word -> wordRepository.save(Word(
      term = word.headword!!,
      lemma = word.lemma!!,
      partOfSpeech = PartOfSpeech.valueOf(word.pos!!),
      languageLevel = LanguageLevel.valueOf(word.cefr!!)
    )) }
  }

  fun vocabularyAddEmbeddings(): Boolean {
    try {
      wordRepository.findAll().forEach { word ->
        wordRepository.save(word, embedding.calculate(word.term))
      }
      return true
    }catch (e: Exception) {
      return false
    }
  }
}
