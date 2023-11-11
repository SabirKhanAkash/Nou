/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.view.activity

import GenericApiResponse
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.akash.nou.R
import com.akash.nou.databinding.ActivityOtpBinding
import com.akash.nou.utils.LoadingDialog
import com.akash.nou.utils.SharedPref
import com.akash.nou.viewmodel.AuthViewModel
import com.akash.nou.viewmodel.viewmodelfactory.AuthViewModelFactory
import com.otpview.OTPListener
import showTopToast

class OTPActivity : AppCompatActivity() {
    private val sharedPref: SharedPref = SharedPref()

    /**
     * Global Variables
     */
    private lateinit var binding: ActivityOtpBinding
    private lateinit var authViewModel: AuthViewModel
    private val loadingDialog: LoadingDialog = LoadingDialog(this@OTPActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        /**
         * Initializations
         */
        binding = ActivityOtpBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.phone.text = "${intent.getStringExtra("phone")} নম্বরে পাঠানো ওটিপি কোডটি টাইপ করুন"

        authViewModel = ViewModelProvider(this, AuthViewModelFactory())[AuthViewModel::class.java]


        /**
         * Event Listeners
         */
        binding.resendOtp.setOnClickListener {
            binding.otpView.resetState()
            authViewModel.verifyPhoneNumber(intent.getStringExtra("phone").toString())
        }

        binding.otpView.otpListener = object : OTPListener {
            override fun onInteractionListener() {

            }

            override fun onOTPComplete(otp: String) {
                binding.otpBtn.performClick()
            }

        }

        binding.otpView.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                binding.otpResponseMsg.visibility = View.INVISIBLE
            }
            false
        }

        binding.otpView.requestFocusOTP()

        binding.otpView.otpListener

        binding.otpBtn.setOnClickListener {
            authViewModel.verifyOTP(
                intent.getStringExtra("phone").toString(),
                binding.otpView.otp!!
            )
        }


        /**
         * LiveData Observer
         */
        authViewModel.otpLiveData.observe(this@OTPActivity) { result ->
            when (result) {
                is GenericApiResponse.Success -> {
                    val resultData = result.data
                    if (resultData.status == "Success") {
                        sharedPref.setString(applicationContext, "authToken", resultData.authToken)
                        sharedPref.setString(
                            applicationContext,
                            "refreshToken",
                            resultData.refreshToken
                        )
                        sharedPref.setUser(applicationContext, "user", resultData.user)

                        binding.otpView.showSuccess()
                        binding.otpView.requestFocusOTP()
                        binding.otpResponseMsg.visibility = View.INVISIBLE
                        startActivity(
                            Intent(
                                this@OTPActivity,
                                HomepageActivity::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        )
                    } else {
                        binding.otpView.showError()
                        binding.otpResponseMsg.visibility = View.VISIBLE
                        binding.otpView.requestFocusOTP()
                    }
                }

                is GenericApiResponse.Error -> {
                    showTopToast(
                        applicationContext,
                        "দুঃখিত! কারিগরি ত্রুটি হয়েছে ☹️",
                        "short",
                        "neutral"
                    )
                }

                else -> {
                    showTopToast(
                        applicationContext,
                        "দুঃখিত! অজানা ত্রুটি হয়েছে ☹️",
                        "short",
                        "neutral"
                    )
                }
            }
        }

        authViewModel.isLoading.observe(this@OTPActivity) { isLoading ->
            if (isLoading)
                loadingDialog.startLoading()
            else
                loadingDialog.dismissLoading()
        }
    }
}