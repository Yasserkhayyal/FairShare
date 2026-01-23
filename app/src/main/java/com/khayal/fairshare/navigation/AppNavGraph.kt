package com.khayal.fairshare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.khayal.navigation.ONBOARDING_NAV_GRAPH
import com.khayal.onboarding.navigation.onboardingNavGraph

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ONBOARDING_NAV_GRAPH) {
        onboardingNavGraph(navController = navController)
    }
}