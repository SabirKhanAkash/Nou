/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.akash.nou.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            this@SplashScreenActivity.startActivity(
                Intent(
                    this@SplashScreenActivity,
                    AuthActivity::class.java
                )
            )
            finish()
        }, 1200)
    }
}