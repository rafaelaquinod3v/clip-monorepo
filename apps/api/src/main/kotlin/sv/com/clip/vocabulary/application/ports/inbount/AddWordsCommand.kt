package sv.com.clip.vocabulary.application.ports.inbount

import sv.com.clip.vocabulary.domain.model.Word

data class AddWordsCommand(val words: List<Word>)
