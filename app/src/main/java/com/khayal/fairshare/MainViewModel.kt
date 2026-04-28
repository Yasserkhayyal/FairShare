package com.khayal.fairshare

import androidx.lifecycle.viewModelScope
import com.khayal.fairshare.presentation.MainIntent
import com.khayal.fairshare.presentation.MainUiState
import com.khayal.mvi.BaseViewModel
import com.khayal.onboarding.domain.IsOnboardingCompletedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val isOnboardingCompletedUseCase: IsOnboardingCompletedUseCase) :
    BaseViewModel<MainIntent>() {

    private val _mainUiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())

    val mainUiState = _mainUiState.asStateFlow()

    private fun checkIsOnboardingFlowCompleted() = viewModelScope.launch {
        isOnboardingCompletedUseCase.isOnboardingComplete().collect {
            _mainUiState.value = _mainUiState.value.copy(shouldShowOnboardingFlow = !it)
        }
    }

    override fun triggerIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.ShouldShowOnboardingFlow -> checkIsOnboardingFlowCompleted()
        }
    }

}