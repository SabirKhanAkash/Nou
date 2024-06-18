/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.view.activity

import Constant
import GenericApiResponse
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.akash.nou.R
import com.akash.nou.databinding.ActivityAuthBinding
import com.akash.nou.utils.LoadingDialog
import com.akash.nou.utils.SharedPref
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
    private val sharedPref: SharedPref = SharedPref()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        setContent {
            AuthActivityContent()
        }


        /**
         * Homepage
         */
        if (sharedPref.getString(applicationContext, "authToken") != "") startActivity(
            Intent(
                this@AuthActivity, HomepageActivity::class.java
            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )


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
            if (binding.phoneNo.text.length == 11 && binding.phoneNo.text.startsWith("01")) authViewModel.verifyPhoneNumber(
                binding.phoneNo.text.toString()
            )

            // Invalid Phone Number
            else showTopToast(
                applicationContext, "১১ ডিজিটের একটি বৈধ নম্বর দিন", "short", "negative"
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
                                this@AuthActivity, OTPActivity::class.java
                            ).putExtra("phone", binding.phoneNo.text.toString())
                        )
                    }
                    else {
                        binding.phoneNo.error = "দুঃখিত! আপনি নিবন্ধিত ব্যবহারকারী না"
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
    }

    @Composable
    fun AuthActivityContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nou_icon),
                    contentDescription = "Logo " +
                            "Here",
                    modifier = Modifier.size(128.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "লগইন করুন",
                    style = TextStyle(
                        fontFamily = Constant().balooda2font,
                        fontSize = 40.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Left
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Text(
                    text = "নৌযানের টিকেট ব্যবস্থাপনার এক বিশ্বস্ত নাম",
                    style = TextStyle(
                        fontFamily = Constant().balooda2font,
                        fontSize = 14.sp,
                        color = Constant().light_gray_2,
                        textAlign = TextAlign.Left
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "ফোন নম্বর",
                    style = TextStyle(
                        fontFamily = Constant().balooda2font,
                        fontSize = 14.sp,
                        color = Constant().light_gray_2,
                        textAlign = TextAlign.Left
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AuthActivityContent()
    }
}