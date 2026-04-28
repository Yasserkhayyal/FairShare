package com.khayal.fairshare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.khayal.designsystem.theme.FairShareTheme
import com.khayal.fairshare.navigation.AppNavGraph
import com.khayal.fairshare.presentation.MainIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FairShareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val mainViewModel by viewModels<MainViewModel>()
                    val mainUiState = mainViewModel.mainUiState.collectAsStateWithLifecycle()
                    mainViewModel.triggerIntent(MainIntent.ShouldShowOnboardingFlow)
                    AppNavGraph(
                        modifier = Modifier.padding(innerPadding),
                        mainUiState.value.shouldShowOnboardingFlow,
                        mainUiState.value.shouldShowAuthFlow
                    )
                }
            }
        }
    }
}