package sv.com.clip.dictionary.api

import java.util.UUID

data class WordDTO(val id: UUID, val word: String, val definition: String?)
