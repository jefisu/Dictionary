package com.jefisu.dictionary.feature.data.remote.dto

import com.jefisu.dictionary.feature.domain.model.Meaning


data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )
    }
}