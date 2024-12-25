package com.akash.nou.presentation

import AppRoute
import Constant
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akash.nou.presentation.screen.AuthScreen
import com.akash.nou.presentation.screen.OtpScreen
import com.akash.nou.presentation.viewmodel.AuthViewModel
import com.akash.nou.service.SharedPrefService
import com.akash.nou.view.feature.homepage.composable.HomeScreen

@Composable
fun App() {
    val navController = rememberNavController()
    val sharedPrefService = SharedPrefService.getInstance(LocalContext.current)
    val authViewModel = hiltViewModel<AuthViewModel>()
    val startingScreen = if (sharedPrefService.getString(Constant.SP_ACCESS_TOKEN_KEY).isEmpty())
        AppRoute.LOGIN
    else AppRoute.HOME

    NavHost(navController = navController, startDestination = startingScreen) {
        composable(AppRoute.LOGIN) {
            AuthScreen(authViewModel, navController, onNavigate = {
                navController.navigate(it.route)
            })
        }
        composable(AppRoute.OTP) { OtpScreen(authViewModel, navController) }
        composable(AppRoute.HOME) { HomeScreen(navController) }
    }
}