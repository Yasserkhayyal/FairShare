package com.khayal.onboarding.di

import com.khayal.onboarding.data.OnboardingRepository
import com.khayal.onboarding.domain.OnboardingRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class OnboardingModule {

    @Binds
    abstract fun bindsOnboardingRepository(
        onboardingRepositoryImp: OnboardingRepositoryImp
    ): OnboardingRepository
}