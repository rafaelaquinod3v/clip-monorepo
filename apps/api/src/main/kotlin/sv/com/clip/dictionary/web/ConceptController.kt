package sv.com.clip.dictionary.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.dictionary.domain.model.Concept
import sv.com.clip.dictionary.domain.repository.ConceptRepository

@RestController
@RequestMapping("/concepts")
class ConceptController(
  private val conceptRepository: ConceptRepository
) {

  @PostMapping
  fun save(@RequestBody(required = true) concept: Concept) : Concept {
    return conceptRepository.save(concept)
  }

  @GetMapping
  fun findAll() : List<Concept> {
    return conceptRepository.findAll()
  }
}
