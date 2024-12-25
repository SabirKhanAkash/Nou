/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.homepage.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.akash.nou.service.EncryptedSharedPrefService
import com.akash.nou.service.SharedPrefService

@Composable
fun ProfileScreen(navController: NavController) {
    val applicationContext = LocalContext.current
    val sharedPrefService = SharedPrefService.getInstance(applicationContext)
    val encryptedSharedPrefService = EncryptedSharedPrefService.getInstance(applicationContext)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                sharedPrefService.clearAll()
                encryptedSharedPrefService.clearAll()
                navController.navigate("login_screen") {
                    popUpTo("home_screen") { inclusive = true }
                }
            },
            text = "Profile Screen\nComing soon...", style = TextStyle(
                textAlign = TextAlign
                    .Center
            )
        )
    }
}