package com.khayal.onboarding.domain

import com.khayal.onboarding.data.OnboardingRepository
import javax.inject.Inject

class OnboardingUseCase @Inject constructor(val repository: OnboardingRepository) {
    suspend fun completeOnboarding() = repository.completeOnboarding()
}