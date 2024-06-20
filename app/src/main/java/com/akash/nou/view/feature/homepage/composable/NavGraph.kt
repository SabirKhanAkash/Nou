package com.akash.nou.view.feature.homepage.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = "ticket") {
        composable("ticket") { TicketScreen() }
        composable("history") { TicketHistoryScreen() }
        composable("profile") { ProfileScreen() }
    }
}





