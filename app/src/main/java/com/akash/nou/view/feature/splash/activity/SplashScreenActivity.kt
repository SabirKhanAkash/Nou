/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.splash.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.akash.nou.view.feature.auth.activity.AuthActivity
import com.akash.nou.view.feature.splash.composable.SplashScreen

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen()
        }

        Handler().postDelayed({
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }, 1200)
    }
}