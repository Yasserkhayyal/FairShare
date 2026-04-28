package com.khayal.onboarding.presentation

import com.khayal.mvi.BaseIntent

sealed class OnboardingIntent : BaseIntent {
    data object CompleteOnboarding : OnboardingIntent()
}