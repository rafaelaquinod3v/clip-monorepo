package sv.com.clip.learning.application.usecases

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sv.com.clip.learning.domain.commands.AddUserWordExclusionCommand
import sv.com.clip.learning.domain.repository.UserWordRepository
import sv.com.clip.learning.infrastructure.UserWordExclusionAdapter

@Service
class AddUserWordExclusionCommandHandler(
  private val exclusionAdapter: UserWordExclusionAdapter,
  private val userWordRepository: UserWordRepository,
) {

  @Transactional
  fun handle(command: AddUserWordExclusionCommand) {
    val cleanTerm: String = command.term.lowercase().trim()

    exclusionAdapter.saveExclusion(command.userId, cleanTerm)

    userWordRepository.deleteByUserIdAndTerm(command.userId, cleanTerm)
  }
}
