package com.khayal.navigation

const val ONBOARDING_NAV_GRAPH = "onboarding_nav_graph"
const val AUTH_NAV_GRAPH = "auth_nav_graph"
const val GROUPS_NAV_GRAPH = "groups_nav_graph"
const val FRIENDS_NAV_GRAPH = "friends_nav_graph"
const val PROFILE_NAV_GRAPH = "profile_nav_graph"

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object LearnMore : Screen("learn_more")
    data object Authentication : Screen("authentication")
    data object Groups : Screen("groups")
    data object Friends : Screen("friends")
    data object Profile : Screen("profile")
    data object GroupDetails : Screen("group_details")
    data object AddExpense : Screen("add_expense")
    data object ExpenseSaved : Screen("expense_saved")
    data object Balances : Screen("balances")
}
