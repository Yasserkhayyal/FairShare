package com.khayal.onboarding.data

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    val isOnboardingComplete: Flow<Boolean>
    suspend fun completeOnboarding()
}