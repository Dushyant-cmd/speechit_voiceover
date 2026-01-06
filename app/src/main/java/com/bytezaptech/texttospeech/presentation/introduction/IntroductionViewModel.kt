package com.bytezaptech.texttospeech.presentation.introduction

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class IntroductionStates {
    data class UseCaseState(val useCasesList: List<String> = listOf("Daily", "Once a week", "At least 2 - 3 times a month")): IntroductionStates()
}

class IntroductionViewModel: ViewModel() {
    private val _useCaseState = MutableStateFlow(value = IntroductionStates.UseCaseState())
    val useCaseState = _useCaseState.asStateFlow()
}