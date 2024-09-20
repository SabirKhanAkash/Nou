/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.homepage.activity

import GenericApiResponse
import NouTheme
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.akash.nou.R
import com.akash.nou.utils.EncryptedSharedPreference
import com.akash.nou.utils.LoadingDialog
import com.akash.nou.utils.SharedPref
import com.akash.nou.view.feature.auth.activity.AuthActivity
import com.akash.nou.view.feature.homepage.composable.HomePageScreen
import com.akash.nou.viewmodel.TicketViewModel
import com.akash.nou.viewmodelfactory.TicketViewModelFactory
import showTopToast

class HomepageActivity : AppCompatActivity() {
    /**
     * Global Variables
     */
    private val sharedPref: SharedPref by lazy { SharedPref() }
    private val encryptedSharedPref: EncryptedSharedPreference by lazy { EncryptedSharedPreference() }
    private lateinit var ticketViewModel: TicketViewModel
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(this@HomepageActivity) }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        ticketViewModel =
            ViewModelProvider(this, TicketViewModelFactory())[TicketViewModel::class.java]

        ticketViewModel.isLoading.observe(this@HomepageActivity) { isLoading ->
            if (isLoading) loadingDialog.startLoading()
            else loadingDialog.dismissLoading()
        }

        ticketViewModel.ticketsLiveData.observe(this@HomepageActivity) { result ->
            when (result) {
                is GenericApiResponse.Success -> {
                    showTopToast(
                        applicationContext,
                        "টিকিট দেখানো হচ্ছে...",
                        "short",
                        "positive"
                    )
                }

                is GenericApiResponse.Error -> {
                    showTopToast(
                        applicationContext,
                        "দুঃখিত! ত্রুটি হয়েছে\nআবার চেষ্টা করুন",
                        "short",
                        "neutral"
                    )
                }

                is GenericApiResponse.Forbidden -> {
                    sharedPref.clearData(applicationContext)
                    encryptedSharedPref.clearData(applicationContext)
                    startActivity(
                        Intent(
                            this@HomepageActivity, AuthActivity::class.java
                        ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    )
                }

                else -> {
                    showTopToast(
                        applicationContext,
                        "দুঃখিত! সার্ভারজনিত ত্রুটি হয়েছে\nআবার চেষ্টা করুন",
                        "short",
                        "neutral"
                    )
                }
            }
        }

        setContent {
            NouTheme {
                HomePageScreen(applicationContext)
            }
        }
    }
}