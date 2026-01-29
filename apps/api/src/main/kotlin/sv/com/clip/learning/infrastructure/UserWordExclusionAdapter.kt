package sv.com.clip.learning.infrastructure

import org.springframework.stereotype.Component
import sv.com.clip.learning.domain.repository.UserWordExclusionRepository
import sv.com.clip.learning.infrastructure.jpa.UserWordExclusionEntity
import sv.com.clip.learning.infrastructure.repository.JpaUserWordExclusionRepository
import java.util.UUID

@Component
class UserWordExclusionAdapter(
  private val exclusionRepository: JpaUserWordExclusionRepository,
) : UserWordExclusionRepository {
  override fun findExclusions(
    userId: UUID, //TODO: use this
  ): Set<String> {
    return exclusionRepository.findAll().map { it.term }.toSet()
  }

  override fun saveExclusion(userId: UUID, term: String) {
    exclusionRepository.save(UserWordExclusionEntity(UUID.randomUUID(), userId, term))
  }

  override fun deleteExclusion(userId: UUID, term: String) {
    exclusionRepository.deleteByTerm(term)
  }

  override fun isExcluded(userId: UUID, term: String): Boolean {
    // return repo.existsByUserIdAndWord(userId, word)
    TODO("Not yet implemented")
  }

}
