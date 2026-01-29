package sv.com.clip.shared

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, Any?>> {
    val errors = ex.bindingResult?.fieldErrors?.associate {
      it.field to it.defaultMessage
    }

    return ResponseEntity.badRequest().body(mapOf(
      "status" to 400,
      "errors" to errors
    ))
  }
}
