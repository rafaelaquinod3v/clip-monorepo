package sv.com.clip.learning.infrastructure.rest.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UserWordRequest(
  @field:NotBlank(message = "El término no puede estar vacío")
  @field:Size(min = 1, max = 50, message = "El término debe tener entre {min} y {max} caracteres")
  // Opcional: Solo letras, apóstrofes y espacios
  @field:Pattern(regexp = "^[\\p{L}'\\s]+$", message = "El término contiene caracteres no válidos")
  val term: String,
  val statusCode: Int? = 2, // Por defecto RECOGNIZED si no se envía
)
