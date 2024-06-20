package com.akash.nou.view.feature.homepage.activity

import NouTheme
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.akash.nou.R
import com.akash.nou.databinding.ActivityHomepageBinding
import com.akash.nou.view.feature.homepage.composable.HomePageScreen

class HomepageActivity : AppCompatActivity() {
    /**
     * Global Variables
     */
    private lateinit var navController: NavController
    private lateinit var binding: ActivityHomepageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)


        /**
         * Initializations
         */
        binding = ActivityHomepageBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigationView, navController)

        setContent {
            NouTheme {
                HomePageScreen()
            }
        }
    }
}