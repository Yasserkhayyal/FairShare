package com.khayal.fairshare.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.khayal.navigation.AUTH_NAV_GRAPH
import com.khayal.navigation.ONBOARDING_NAV_GRAPH
import com.khayal.onboarding.navigation.onboardingNavGraph

@Composable
fun AppNavGraph(
    modifier: Modifier,
    shouldShowOnboarding: Boolean,
    shouldShowAuthFlow: Boolean,
) {
    val navController = rememberNavController()
    val startDestination = if (shouldShowOnboarding) ONBOARDING_NAV_GRAPH else AUTH_NAV_GRAPH

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        onboardingNavGraph(navController = navController)
    }
}