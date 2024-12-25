/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.presentation

import AppRoute
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akash.nou.presentation.screen.TicketScreen
import com.akash.nou.view.feature.homepage.composable.ProfileScreen
import com.akash.nou.view.feature.homepage.composable.TicketHistoryScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable

fun NavGraph(
    navHostController: NavHostController,
    modifier: Modifier,
    navController: NavController
) {
    NavHost(navController = navHostController, startDestination = AppRoute.TICKET) {
        composable(AppRoute.TICKET) { TicketScreen(navController) }
        composable(AppRoute.TICKET_HISTORY) { TicketHistoryScreen(navController) }
        composable(AppRoute.PROFILE) { ProfileScreen(navController) }
    }
}





