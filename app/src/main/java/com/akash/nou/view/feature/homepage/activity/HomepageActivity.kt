/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.homepage.activity

import NouTheme
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.akash.nou.R
import com.akash.nou.utils.SharedPref
import com.akash.nou.view.feature.homepage.composable.HomePageScreen

class HomepageActivity : AppCompatActivity() {
    /**
     * Global Variables
     */
    private val sharedPref: SharedPref by lazy { SharedPref() }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        setContent {
            NouTheme {
                HomePageScreen(applicationContext, sharedPref)
            }
        }
    }
}