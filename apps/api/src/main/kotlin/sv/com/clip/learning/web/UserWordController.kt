package sv.com.clip.learning.web

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.learning.application.usecases.AddUserWordCommandHandler
import sv.com.clip.learning.domain.WordStatus
import sv.com.clip.learning.domain.commands.AddUserWordCommand
import sv.com.clip.learning.infrastructure.rest.dto.UserWordRequest
import java.util.UUID

@RestController
@RequestMapping("/learning/user-words")
class UserWordController(
  private val handler: AddUserWordCommandHandler
) {

  @PostMapping
  fun addUserWord(@Valid @RequestBody request: UserWordRequest) {
    val command = AddUserWordCommand(
      UUID.randomUUID(),
      request.term,
      WordStatus.fromCode(request.statusCode),
      )
    handler.handle(command)
  }
}
