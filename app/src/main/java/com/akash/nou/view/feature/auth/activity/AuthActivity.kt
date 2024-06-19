/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.view.feature.auth.activity

import GenericApiResponse
import NouTheme
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.akash.nou.R
import com.akash.nou.utils.LoadingDialog
import com.akash.nou.utils.SharedPref
import com.akash.nou.view.feature.auth.screen.AuthActivityContent
import com.akash.nou.view.feature.homepage.activity.HomepageActivity
import com.akash.nou.viewmodel.AuthViewModel
import com.akash.nou.viewmodel.viewmodelfactory.AuthViewModelFactory
import showTopToast

class AuthActivity : AppCompatActivity() {
    /**
     * Global Variables
     */
    private lateinit var authViewModel: AuthViewModel
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(this) }
    private val sharedPref: SharedPref by lazy { SharedPref() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        /**
         * Redirect to Homepage if logged in
         */
        if (sharedPref.getString(applicationContext, "authToken") != "") {
            startActivity(
                Intent(
                    this@AuthActivity, HomepageActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }

        authViewModel = ViewModelProvider(this, AuthViewModelFactory())[AuthViewModel::class.java]

        /**
         * LiveData Observer
         */
        authViewModel.authLiveData.observe(this@AuthActivity) { result ->
            when (result) {
                is GenericApiResponse.Success -> {
                    val resultData = result.data
                    if (resultData.status == "Success") {
                        startActivity(
                            Intent(
                                this@AuthActivity, OTPActivity::class.java
                            ).putExtra("phone", authViewModel.phoneNumber.value.toString())
                        )
                    }
                    else {
                        showTopToast(
                            applicationContext, "দুঃখিত! আপনি নিবন্ধিত ব্যবহারকারী না", "short",
                            "negative"
                        )
                    }
                }

                is GenericApiResponse.Error -> {
                    showTopToast(
                        applicationContext, "দুঃখিত! কারিগরি ত্রুটি হয়েছে ☹️", "short", "neutral"
                    )
                }

                else -> {
                    showTopToast(
                        applicationContext, "দুঃখিত! অজানা ত্রুটি হয়েছে ☹️", "short", "neutral"
                    )
                }
            }
        }

        authViewModel.isLoading.observe(this@AuthActivity) { isLoading ->
            if (isLoading) loadingDialog.startLoading()
            else loadingDialog.dismissLoading()
        }

        setContent {
            NouTheme {
                AuthActivityContent(applicationContext, authViewModel)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AuthActivityContent(applicationContext, authViewModel)
    }
}