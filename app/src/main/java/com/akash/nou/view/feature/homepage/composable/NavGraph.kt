/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.homepage.composable

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akash.nou.utils.SharedPref

@RequiresApi(Build.VERSION_CODES.N)
@Composable

fun NavGraph(context: Context, navController: NavHostController, sharedPref: SharedPref,
             modifier: Modifier) {
    NavHost(navController = navController, startDestination = "ticket") {
        composable("ticket") { TicketScreen(context, sharedPref) }
        composable("history") { TicketHistoryScreen() }
        composable("profile") { ProfileScreen() }
    }
}





