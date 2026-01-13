package sv.com.clip.library.domain.services

import sv.com.clip.library.domain.aggregates.SourceMaterial

interface ImportRawTextSourceMaterial {
  fun import(rawText: String): SourceMaterial
}
