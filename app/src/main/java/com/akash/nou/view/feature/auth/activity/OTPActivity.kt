/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.auth.activity

import GenericApiResponse
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.akash.nou.R
import com.akash.nou.utils.LoadingDialog
import com.akash.nou.utils.SharedPref
import com.akash.nou.view.feature.auth.composable.OTPScreen
import com.akash.nou.view.feature.homepage.activity.HomepageActivity
import com.akash.nou.viewmodel.AuthViewModel
import com.akash.nou.viewmodelfactory.AuthViewModelFactory
import showTopToast

class OTPActivity : AppCompatActivity() {

    /**
     * Global Variables
     */
    private val sharedPref: SharedPref by lazy { SharedPref() }
    private lateinit var authViewModel: AuthViewModel
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(this@OTPActivity) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        /**
         * Initializations
         */
        authViewModel = ViewModelProvider(this, AuthViewModelFactory())[AuthViewModel::class.java]

        /**
         * LiveData Observer
         */
        authViewModel.otpLiveData.observe(this@OTPActivity) { result ->
            when (result) {
                is GenericApiResponse.Success -> {
                    val resultData = result.data
                    if (resultData.status == "Success") {
                        authViewModel.setOtpVerified(true)
                        sharedPref.setString(applicationContext, "accessToken", resultData.accessToken)
                        sharedPref.setString(
                            applicationContext, "refreshToken", resultData.refreshToken
                        )
                        sharedPref.setUser(applicationContext, "user", resultData.user)
                        startActivity(
                            Intent(
                                this@OTPActivity, HomepageActivity::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        )
                    }
                    else {
                        authViewModel.setOtpVerified(false)
                    }
                }

                is GenericApiResponse.Error -> {
                    authViewModel.setOtpVerified(false)
                    showTopToast(
                        applicationContext, "দুঃখিত! কারিগরি ত্রুটি হয়েছে ☹️", "short", "neutral"
                    )
                }

                is GenericApiResponse.Forbidden -> {
                    authViewModel.setOtpVerified(false)
                    showTopToast(
                        applicationContext, "ওটিপি কোডটি ভুল হয়েছে", "short", "neutral"
                    )
                }

                else -> {
                    authViewModel.setOtpVerified(false)
                    showTopToast(
                        applicationContext, "দুঃখিত! অজানা ত্রুটি হয়েছে ☹️", "short", "neutral"
                    )
                }
            }
        }

        authViewModel.isLoading.observe(this@OTPActivity) { isLoading ->
            if (isLoading) loadingDialog.startLoading()
            else loadingDialog.dismissLoading()
        }

        setContent {
            OTPScreen(authViewModel, intent.getStringExtra("phone"))
        }
    }
}