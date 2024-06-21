package com.akash.nou.view.feature.homepage.activity

import NouTheme
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.akash.nou.R
import com.akash.nou.view.feature.homepage.composable.HomePageScreen

class HomepageActivity : AppCompatActivity() {
    /**
     * Global Variables
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        setContent {
            NouTheme {
                HomePageScreen(applicationContext)
            }
        }
    }
}