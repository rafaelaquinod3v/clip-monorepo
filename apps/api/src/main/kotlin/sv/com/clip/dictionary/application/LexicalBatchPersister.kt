package sv.com.clip.dictionary.application

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import sv.com.clip.dictionary.domain.model.Concept
import sv.com.clip.dictionary.domain.model.LexicalEntry
import sv.com.clip.dictionary.domain.repository.ConceptRepository
import sv.com.clip.dictionary.domain.repository.LexicalEntryRepository

@Component
class LexicalBatchPersister(
  private val lexicalEntryRepository: LexicalEntryRepository,
  private val conceptRepository: ConceptRepository
) {
  @Transactional // Transacción por lote
  fun saveBatch(entries: List<LexicalEntry>) {
    // 1. Lógica de conceptos ILI (mencionada antes)
    val uniqueIlis = entries.flatMap { it.senses }.mapNotNull { it.conceptIli }.filter { it.isNotBlank() }.distinct()

    if (uniqueIlis.isNotEmpty()) {
      val existingIds = conceptRepository.findExistingIlis(uniqueIlis)
        .map { it.ili }
        .toSet() // O(1) lookup

      val newConcepts = uniqueIlis.filter { it !in existingIds }.map { Concept(it) }
      if (newConcepts.isNotEmpty()) {
        conceptRepository.saveAll(newConcepts)
      }
    }
    // 2. Guardar Entradas
    lexicalEntryRepository.saveAll(entries)
  }
}
