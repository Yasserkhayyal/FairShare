package com.khayal.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.khayal.navigation.ONBOARDING_NAV_GRAPH
import com.khayal.navigation.Screen
import com.khayal.onboarding.welcome.Welcome

fun NavGraphBuilder.onboardingNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Welcome.route, route = ONBOARDING_NAV_GRAPH) {
        composable(route = Screen.Welcome.route) {
            Welcome(
                onGetStartedClick = {},
                onLearnMoreClick = {}
            )
        }
    }
}