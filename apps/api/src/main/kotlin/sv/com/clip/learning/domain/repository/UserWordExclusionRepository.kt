package sv.com.clip.learning.domain.repository

import org.jmolecules.ddd.annotation.Repository
import java.util.UUID

@Repository
interface UserWordExclusionRepository {
  fun findExclusions(userId: UUID) : Set<String>
  fun saveExclusion(userId: UUID, term: String)
  fun deleteExclusion(userId: UUID, term: String)
  fun isExcluded(userId: UUID, term: String): Boolean
}
