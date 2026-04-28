package com.khayal.onboarding.domain

import com.khayal.onboarding.data.OnboardingRepository
import javax.inject.Inject

class IsOnboardingCompletedUseCase @Inject constructor(val repository: OnboardingRepository) {
    fun isOnboardingComplete() = repository.isOnboardingComplete
}