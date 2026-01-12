package sv.com.clip.student.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.student.domain.Student

@RestController
@RequestMapping("/api")
class StudentController {

  @GetMapping("/students")
  fun getStudent(): List<Student> {
    return listOf(
        Student("John"),
        Student("Mary"),
        Student("James")
    )
  }
}
