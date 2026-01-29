package sv.com.clip.learning.application.usecases

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sv.com.clip.learning.domain.UserWord
import sv.com.clip.learning.domain.WordStatus
import sv.com.clip.learning.domain.commands.AddUserWordCommand
import sv.com.clip.learning.domain.repository.UserWordExclusionRepository
import sv.com.clip.learning.domain.repository.UserWordRepository

@Service
class AddUserWordCommandHandler(
  private val userWordRepository: UserWordRepository,
  private val exclusions: UserWordExclusionRepository
) {
  @Transactional
  fun handle(command: AddUserWordCommand) {
    val cleanTerm = command.term.lowercase().trim()

    exclusions.deleteExclusion(command.userId, cleanTerm)

    val existing = userWordRepository.findByUserIdAndTerm(command.userId, cleanTerm)
    if (existing != null) {
      existing.customDefinition = command.term
      existing.status = command.status
      userWordRepository.save(existing)
    }else {
      val newWord = UserWord(
        userId = command.userId,
        term = cleanTerm,
        lexicalEntryId = null, // Marcador de que NO está en el dict oficial
        customDefinition = command.term,
        status = command.status,
        isManualLexicalEntry = true
      )
      userWordRepository.save(newWord)
    }
  }
}
