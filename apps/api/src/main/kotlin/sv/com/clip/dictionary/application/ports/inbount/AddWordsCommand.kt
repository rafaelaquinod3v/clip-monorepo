package sv.com.clip.dictionary.application.ports.inbount

import sv.com.clip.dictionary.domain.model.Word

data class AddWordsCommand(val words: List<Word>)
