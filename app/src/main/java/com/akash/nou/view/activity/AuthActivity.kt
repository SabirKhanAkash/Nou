/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.view.activity

import GenericApiResponse
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.akash.nou.R
import com.akash.nou.databinding.ActivityAuthBinding
import com.akash.nou.utils.LoadingDialog
import com.akash.nou.viewmodel.AuthViewModel
import com.akash.nou.viewmodel.viewmodelfactory.AuthViewModelFactory
import showTopToast

class AuthActivity : AppCompatActivity() {
    /**
     * Global Variables
     */
    private lateinit var binding: ActivityAuthBinding
    private lateinit var authViewModel: AuthViewModel
    private val loadingDialog: LoadingDialog = LoadingDialog(this@AuthActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)


        /**
         * Initializations
         */
        binding = ActivityAuthBinding.inflate(layoutInflater)

        setContentView(binding.root)

        authViewModel = ViewModelProvider(this, AuthViewModelFactory())[AuthViewModel::class.java]


        /**
         * Event Listeners
         */
        binding.loginBtn.setOnClickListener {
            // Valid Phone Number
            if (binding.phoneNo.text.length == 11 && binding.phoneNo.text.startsWith("01"))
                authViewModel.verifyPhoneNumber(binding.phoneNo.text.toString())

            // Invalid Phone Number
            else
                showTopToast(
                    applicationContext,
                    "১১ ডিজিটের একটি বৈধ নম্বর দিন",
                    "short",
                    "negative"
                )
        }


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
                                this@AuthActivity,
                                OTPActivity::class.java
                            ).putExtra("phone", binding.phoneNo.text.toString())
                        )
                    } else {
                        binding.phoneNo.error = "দুঃখিত! আপনি নিবন্ধিত ব্যবহারকারী না"
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

        authViewModel.isLoading.observe(this@AuthActivity) { isLoading ->
            if (isLoading)
                loadingDialog.startLoading()
            else
                loadingDialog.dismissLoading()
        }
    }
}