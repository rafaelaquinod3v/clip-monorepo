package sv.com.clip.dictionary.domain.model

import java.util.UUID

class SenseAxis(
  id: UUID,
  val connectedSenses: List<SenseId>
) {
}
