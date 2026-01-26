package com.khayal.fairshare.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.khayal.navigation.ONBOARDING_NAV_GRAPH
import com.khayal.onboarding.navigation.onboardingNavGraph

@Composable
fun AppNavGraph(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ONBOARDING_NAV_GRAPH
    ) {
        onboardingNavGraph(navController = navController)
    }
}