package com.khayal.fairshare.presentation

import com.khayal.mvi.BaseIntent

sealed class MainIntent : BaseIntent {
    object ShouldShowOnboardingFlow : MainIntent()
}