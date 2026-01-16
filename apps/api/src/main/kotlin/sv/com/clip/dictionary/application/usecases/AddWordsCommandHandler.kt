package sv.com.clip.dictionary.application.usecases;

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional
import sv.com.clip.dictionary.application.ports.inbount.AddWordsCommand
import sv.com.clip.dictionary.domain.events.WordsAddedEvent
import sv.com.clip.dictionary.domain.model.Word
import sv.com.clip.dictionary.domain.repository.WordRepository;
import sv.com.clip.dictionary.infrastructure.DjlEmbeddingAdapter

@Service
class AddWordsCommandHandler(
  private val repo:WordRepository,
  private val eventPublisher: ApplicationEventPublisher,
  private val embedding: DjlEmbeddingAdapter
) {
  // indispensable
  @Transactional
  fun handle(command: AddWordsCommand) {
    // Persistimos la colección de palabras
    //repo.saveAll(command.words)
    command.words.forEach { word ->
      repo.save(word, embedding.calculate(word.term))
    }
    // Publicamos un único evento con la lista de IDs para el Read Model
    val ids = command.words.map { it.id }
    eventPublisher.publishEvent(WordsAddedEvent(ids))
  }
}
