package com.khayal.storage

import androidx.datastore.preferences.core.booleanPreferencesKey

object SharedPrefsKeys {
    val IS_ONBOARDING_COMPLETE = booleanPreferencesKey("is_onboarding_complete")
}