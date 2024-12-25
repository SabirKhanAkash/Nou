package com.akash.nou.util

import AppRoute
import androidx.navigation.NavController
import com.akash.nou.service.EncryptedSharedPrefService
import com.akash.nou.service.SharedPrefService

fun logOutUser(
    sharedPrefService: SharedPrefService,
    encryptedSharedPrefService: EncryptedSharedPrefService,
    navController: NavController, currentRoute: String
) {
    sharedPrefService.clearAll()
    encryptedSharedPrefService.clearAll()
    navController.navigate(AppRoute.LOGIN) {
        popUpTo(currentRoute) { inclusive = true }
    }
}