package com.khayal.onboarding.presentation

import androidx.lifecycle.viewModelScope
import com.khayal.mvi.BaseViewModel
import com.khayal.onboarding.domain.OnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(val onboardingUseCase: OnboardingUseCase) :
    BaseViewModel<OnboardingIntent>() {

    suspend fun completeOnboarding() = onboardingUseCase.completeOnboarding()

    override fun triggerIntent(intent: OnboardingIntent) {
        viewModelScope.launch {
            when (intent) {
                is OnboardingIntent.CompleteOnboarding -> completeOnboarding()
            }
        }
    }
}