package com.jefisu.dictionary.feature.presentation.home

import com.jefisu.dictionary.feature.domain.model.WordInfo

data class HomeState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
