package com.jefisu.dictionary.feature.domain.repository

import com.jefisu.dictionary.core.util.Resource
import com.jefisu.dictionary.feature.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}