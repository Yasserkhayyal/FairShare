package com.khayal.onboarding.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.khayal.navigation.ONBOARDING_NAV_GRAPH
import com.khayal.navigation.Screen
import com.khayal.onboarding.presentation.OnboardingIntent
import com.khayal.onboarding.presentation.OnboardingViewModel
import com.khayal.onboarding.presentation.learnmore.LearnMore
import com.khayal.onboarding.presentation.welcome.Welcome

fun NavGraphBuilder.onboardingNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Welcome.route, route = ONBOARDING_NAV_GRAPH) {
        composable(route = Screen.Welcome.route) {
            val currentBackStackEntry = navController.currentBackStackEntryAsState()
            val parentEntry = remember(currentBackStackEntry.value) {
                navController.getBackStackEntry(ONBOARDING_NAV_GRAPH)
            }
            val onboardingViewModel: OnboardingViewModel = hiltViewModel(parentEntry)
            Welcome(
                onGetStartedClick = { onboardingViewModel.triggerIntent(OnboardingIntent.CompleteOnboarding) },
                onLearnMoreClick = { navController.navigate(Screen.LearnMore.route) }
            )
        }
        composable(route = Screen.LearnMore.route) {
            val currentBackStackEntry = navController.currentBackStackEntryAsState()
            val parentEntry = remember(currentBackStackEntry.value) {
                navController.getBackStackEntry(ONBOARDING_NAV_GRAPH)
            }
            val onboardingViewModel: OnboardingViewModel = hiltViewModel(parentEntry)

            LearnMore(
                onSkipButtonClicked = { /* navigate to auth flow */ },
                onNextButtonClicked = { onboardingViewModel.triggerIntent(OnboardingIntent.CompleteOnboarding) }
            )
        }
    }
}
