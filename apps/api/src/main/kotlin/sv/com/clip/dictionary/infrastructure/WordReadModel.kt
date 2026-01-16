package sv.com.clip.dictionary.infrastructure

import java.util.UUID

data class WordReadModel(
    val id: UUID,
    val term: String,
    val lemma: String?,
    val spanishTranslation: String?
)
