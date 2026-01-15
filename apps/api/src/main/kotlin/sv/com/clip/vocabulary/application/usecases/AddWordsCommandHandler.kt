package sv.com.clip.vocabulary.application.usecases;

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional
import sv.com.clip.vocabulary.application.ports.inbount.AddWordsCommand
import sv.com.clip.vocabulary.domain.events.WordsAddedEvent
import sv.com.clip.vocabulary.domain.repository.WordRepository;

@Service
class AddWordsCommandHandler(
  private val repo:WordRepository,
  private val eventPublisher: ApplicationEventPublisher
) {
  @Transactional
  fun handle(command: AddWordsCommand) {
    // Persistimos la colección de palabras
    repo.saveAll(command.words)

    // Publicamos un único evento con la lista de IDs para el Read Model
    val ids = command.words.map { it.id }
    eventPublisher.publishEvent(WordsAddedEvent(ids))
  }
}
