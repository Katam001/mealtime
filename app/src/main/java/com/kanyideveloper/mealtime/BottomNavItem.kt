
package com.kanyideveloper.mealtime

import com.kanyideveloper.mealtime.core.R
import com.kanyideveloper.mealtime.navigation.NavGraphs
import com.ramcosta.composedestinations.spec.NavGraphSpec

sealed class BottomNavItem(var title: String, var icon: Int, var screen: NavGraphSpec) {
    object Home : BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_home,
        screen = NavGraphs.home
    )

    object KitchenTimer : BottomNavItem(
        title = "Timer",
        icon = R.drawable.ic_timer,
        screen = NavGraphs.kitchenTimer
    )

    object MealPlanner : BottomNavItem(
        title = "Planner",
        icon = R.drawable.date_range,
        screen = NavGraphs.mealPlanner
    )

    object Favorites : BottomNavItem(
        title = "Favorites",
        icon = R.drawable.ic_favorites,
        screen = NavGraphs.favorites
    )

    object Settings : BottomNavItem(
        title = "Settings",
        icon = R.drawable.ic_settings,
        screen = NavGraphs.settings
    )
}
