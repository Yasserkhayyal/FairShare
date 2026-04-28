package com.khayal.onboarding.domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.khayal.onboarding.data.OnboardingRepository
import com.khayal.storage.SharedPrefsKeys.IS_ONBOARDING_COMPLETE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnboardingRepositoryImp @Inject constructor(
    val prefsDataStore: DataStore<Preferences>
) : OnboardingRepository {

    override val isOnboardingComplete: Flow<Boolean> = prefsDataStore.data.map { preferences ->
        preferences[IS_ONBOARDING_COMPLETE] ?: false
    }

    override suspend fun completeOnboarding() {
        prefsDataStore.edit {
            it[IS_ONBOARDING_COMPLETE] = true
        }
    }
}