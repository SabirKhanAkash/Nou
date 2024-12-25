/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.presentation.screen

import Constant
import CustomToast
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.akash.nou.R
import com.akash.nou.model.AuthRequest
import com.akash.nou.presentation.viewmodel.AuthViewModel
import com.akash.nou.service.EncryptedSharedPrefService
import com.akash.nou.service.SharedPrefService
import com.akash.nou.util.ApiState
import com.mukeshsolanki.OTP_VIEW_TYPE_BORDER
import com.mukeshsolanki.OtpView

@Composable
fun OtpScreen(authViewModel: AuthViewModel, navController: NavHostController) {
    val applicationContext = LocalContext.current
    val sharedPrefService = SharedPrefService.getInstance(applicationContext)
    val encryptedSharedPrefService = EncryptedSharedPrefService.getInstance(applicationContext)
    val otpVerifyState by authViewModel.otpVerifyState.collectAsState()
    val otpValue by authViewModel.otp.observeAsState(initial = "")
    val phoneNumber by authViewModel.phoneNumber.observeAsState(initial = "")
    val isOTPVerified by authViewModel.isOTPVerified.observeAsState(initial = false)
    val otpBorderColor: Color
    val wrongOtpMsgColor: Color
    if (isOTPVerified) {
        otpBorderColor = Constant.toast_text_green
        wrongOtpMsgColor = Color.White
    }
    else if (otpValue.length == 4) {
        otpBorderColor = Constant.toast_text_red
        wrongOtpMsgColor = Constant.toast_text_red
    }
    else {
        otpBorderColor = Constant.light_gray_2
        wrongOtpMsgColor = Color.White
    }

    LaunchedEffect(key1 = otpVerifyState) {
        if (otpVerifyState is ApiState.Success) {
            val resultData = (otpVerifyState as ApiState.Success).data
            if (resultData.status == "Success") {
                authViewModel.setOtpVerified(true)
                sharedPrefService.setString(
                    Constant.SP_ACCESS_TOKEN_KEY,
                    resultData.accessToken.toString()
                )
                encryptedSharedPrefService.setString(
                    Constant.ESP_REFRESH_TOKEN_KEY,
                    resultData.refreshToken.toString()
                )
                sharedPrefService.setUser("user", resultData.user)
                navController.navigate("home_screen")
            }
            else {
                authViewModel.setOtpVerified(false)
            }
        }
        if (otpVerifyState is ApiState.Error) {
            authViewModel.setOtpVerified(false)
            Log.d(Constant.LOG_TAG, "${(otpVerifyState as ApiState.Error).errorMsg}")
            CustomToast(
                applicationContext, "দুঃখিত! কারিগরি ত্রুটি হয়েছে ☹️ ${
                    (otpVerifyState as ApiState
                    .Error).errorMsg
                }", "short", "neutral"
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_yacht), contentDescription = "yatch " +
                    "icon"
        )
        Column(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "ওটিপি কোড",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 40.sp,
                    fontFamily = Constant.balooda2font,
                    textAlign = TextAlign.Start
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = "$phoneNumber নম্বরে পাঠানো ওটিপি কোডটি টাইপ করুন",
                style = TextStyle(
                    color = Constant.light_gray_2,
                    fontSize = 13.sp,
                    fontFamily = Constant.balooda2font,
                    textAlign = TextAlign.Start
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = "ওটিপি পাননি? আবার কোড নিন",
                style = TextStyle(
                    color = Constant.app_theme_color,
                    fontSize = 13.sp,
                    fontFamily = Constant.balooda2font,
                    textAlign = TextAlign.Start
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.clickable {
                    authViewModel.setOtp("")
                    authViewModel.login(
                        AuthRequest(
                            authSource = Constant.AUTH_SOURCE,
                            phoneNo =
                            phoneNumber,
                        ), sharedPrefService, encryptedSharedPrefService
                    )
                }
            )
        }
        Column(
            modifier = Modifier.padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OtpView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 8.dp),
                charBackground = Constant.light_gray,
                charColor = otpBorderColor,
                otpText = otpValue,
                onOtpTextChange = {
                    authViewModel.setOtp(it)
                    if (it.length == 4) {
                        authViewModel.verifyOTP(
                            AuthRequest(
                                authSource = Constant.AUTH_SOURCE, phoneNo =
                                phoneNumber, otp = it
                            ),
                            sharedPrefService,
                            encryptedSharedPrefService
                        )
                    }
                },
                type = OTP_VIEW_TYPE_BORDER,
                password = false,
                containerSize = 45.dp,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Text(
                text = "ওটিপি কোডটি ভুল হয়েছে",
                style = TextStyle(
                    color = wrongOtpMsgColor,
                    fontSize = 13.sp,
                    fontFamily = Constant.balooda2font,
                    textAlign = TextAlign.Center
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    OTPScreen()
//}