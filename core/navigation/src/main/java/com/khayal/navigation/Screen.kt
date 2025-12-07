package com.khayal.navigation

const val AUTH_NAV_GRAPH = "authNavGraph"
const val GROUPS_NAV_GRAPH = "groupsNavGraph"
const val FRIENDS_NAV_GRAPH = "friendsNavGraph"
const val PROFILE_NAV_GRAPH = "profileNavGraph"

sealed class Screen(val name: String){
    data object Welcome: Screen("Welcome")
    data object LearnMore: Screen("LearnMore")
    data object Authentication: Screen("Authentication")
    data object Groups: Screen("Groups")
    data object Friends: Screen("Friends")
    data object Profile: Screen("Profile")
    data object GroupDetails: Screen("GroupDetails")
    data object AddExpense: Screen("AddExpense")
    data object ExpenseSaved: Screen("ExpenseSaved")
    data object Balances: Screen("Balances")
}
