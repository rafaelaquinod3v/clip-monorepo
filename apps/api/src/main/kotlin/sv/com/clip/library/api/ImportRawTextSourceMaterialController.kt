package sv.com.clip.library.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sv.com.clip.library.domain.services.ImportRawTextSourceMaterial

@RestController
@RequestMapping("/content")
class ImportRawTextSourceMaterialController(val importRawTextSourceMaterial: ImportRawTextSourceMaterial) {

  @PostMapping("/import/raw-text")
  fun importRawTextContent(@RequestBody request: ImportRawTextSourceMaterialRequest): Boolean {
    println(request.rawText)
    println(importRawTextSourceMaterial.import(request.rawText))
    return true
  }
}
