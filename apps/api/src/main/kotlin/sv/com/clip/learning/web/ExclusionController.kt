package sv.com.clip.learning.web

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.learning.application.usecases.AddUserWordExclusionCommandHandler
import sv.com.clip.learning.domain.commands.AddUserWordExclusionCommand
import sv.com.clip.learning.infrastructure.rest.dto.ExcludeRequest
import java.util.UUID

@RestController
@RequestMapping("/learning/exclusions")
class ExclusionController(
  private val handler: AddUserWordExclusionCommandHandler
) {
  @PostMapping
  fun excludeTerm(@Valid @RequestBody request: ExcludeRequest) {
    handler.handle(AddUserWordExclusionCommand(UUID.randomUUID(), request.term))
  }
}
