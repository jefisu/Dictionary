package com.jefisu.dictionary.feature.domain.use_case

import com.jefisu.dictionary.core.util.Resource
import com.jefisu.dictionary.feature.domain.model.WordInfo
import com.jefisu.dictionary.feature.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()) {
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}