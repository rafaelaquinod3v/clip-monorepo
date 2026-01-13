package sv.com.clip.library.domain.repository

import sv.com.clip.library.domain.aggregates.SourceMaterial

interface SourceMaterialRepository {
  fun save(sourceMaterial: SourceMaterial)
}
