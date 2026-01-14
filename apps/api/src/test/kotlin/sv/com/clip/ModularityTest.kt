package sv.com.clip

import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules

class ModularityTest {

  val modules = ApplicationModules.of(ApiApplication::class.java)

  @Test
  fun verificarEstructuraModular() {
    modules.verify()
  }
}
