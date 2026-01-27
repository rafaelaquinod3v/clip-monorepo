package sv.com.clip.dictionary.application.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sv.com.clip.dictionary.application.LexicalBatchPersister
import sv.com.clip.dictionary.domain.gateways.TranslationGateway
import sv.com.clip.dictionary.domain.model.LexicalEntry
import sv.com.clip.dictionary.domain.repository.LexicalEntryRepository
import sv.com.clip.dictionary.domain.valueObjects.Language

@Service
class LexiconSyncService(
  private val entryRepository: LexicalEntryRepository,
  private val translationGateway: TranslationGateway,
  private val batchPersister: LexicalBatchPersister
) {
  @Transactional
  fun syncMissingSpanishEntries(batchSize: Int = 100) {
    // 1. Encontrar ILIs que están en EN pero no en ES
//    val missingIlis = entryRepository.findIlisMissingInLang(Language.EN, Language.ES, batchSize)

//    missingIlis.forEach { ili ->
//      val englishSense = senseRepository.findByConceptIliAndLang(ili, Language.EN)

      // 2. Traducir (Llamada a tu servicio de IA/Traducción)
//      val translation = translationGateway.translate("hello", Language.EN, Language.ES)

      // 3. Crear y guardar nueva entrada en ES
//      val newEntry = LexicalEntry(
//        lemma = translation.lemma,
//        lexiconId = lexiconEsId,
//        partOfSpeech = englishSense.lexicalEntry.partOfSpeech,
//        senses = listOf(Sense(conceptIli = ili, definition = translation.definition))
//      )
//      lexicalEntryRepository.save(newEntry)
//    }
  }
  fun syncEnglishToSpanish() {
    // 1. Obtener sentidos en inglés que no tienen equivalente en español vía ILI
    // 2. Por cada lote:
    //    a. Traducir usando translationProvider
    //    b. Construir nuevos objetos LexicalEntry de dominio
    //    c. batchPersister.saveBatch(nuevasEntradas)
  }

}
