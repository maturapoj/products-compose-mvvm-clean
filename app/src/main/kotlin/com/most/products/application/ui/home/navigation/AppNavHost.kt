package com.most.products.application.ui.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

const val root = "root"

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = homeScreen,
        route = root,
    ) {
        homeScreen(navController)
    }
}