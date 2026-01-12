package sv.com.clip

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiApplication

fun main(args: Array<String>) {
  println("Hello World")
	runApplication<ApiApplication>(*args)
}
