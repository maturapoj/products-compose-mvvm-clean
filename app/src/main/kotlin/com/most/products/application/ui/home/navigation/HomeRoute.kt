package com.most.products.application.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.most.products.application.ui.home.compose.HomeMainScreen

const val homeScreen = "home_screen"

fun NavGraphBuilder.homeScreen(
    navController: NavHostController,
) {
    composable(route = homeScreen) {
        HomeMainScreen(navController)
    }
}