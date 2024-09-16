/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.auth.composable

import Constant
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.R
import com.akash.nou.dto.AuthDto
import com.akash.nou.viewmodel.AuthViewModel
import com.mukeshsolanki.OTP_VIEW_TYPE_BORDER
import com.mukeshsolanki.OtpView

@Composable
fun OTPScreen(authViewModel: AuthViewModel, phoneNumber: String?) {
    val otpValue by authViewModel.otp.observeAsState(initial = "")
    val isOTPVerified by authViewModel.isOTPVerified.observeAsState(initial = false)
    val otpBorderColor: Color
    val wrongOtpMsgColor: Color
    if (isOTPVerified) {
        otpBorderColor = Constant().toast_text_green
        wrongOtpMsgColor = Color.White
    }
    else if (otpValue.length == 4) {
        otpBorderColor = Constant().toast_text_red
        wrongOtpMsgColor = Constant().toast_text_red
    }
    else {
        otpBorderColor = Constant().light_gray_2
        wrongOtpMsgColor = Color.White
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
            painter = painterResource(id = R.drawable.yacht_icon), contentDescription = "yatch " +
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
                    fontFamily = Constant().balooda2font,
                    textAlign = TextAlign.Start
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = "${phoneNumber.toString()} নম্বরে পাঠানো ওটিপি কোডটি টাইপ করুন",
                style = TextStyle(
                    color = Constant().light_gray_2,
                    fontSize = 13.sp,
                    fontFamily = Constant().balooda2font,
                    textAlign = TextAlign.Start
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = "ওটিপি পাননি? আবার কোড নিন",
                style = TextStyle(
                    color = Constant().app_theme_color,
                    fontSize = 13.sp,
                    fontFamily = Constant().balooda2font,
                    textAlign = TextAlign.Start
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.clickable {
                    val authDto = AuthDto().apply {
                        phoneNo = phoneNumber.toString()
                        authSource = "nou-mobile"
                    }
                    authViewModel.setOtp("")
                    authViewModel.login(authDto)
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
                charBackground = Constant().light_gray,
                charColor = otpBorderColor,
                otpText = otpValue,
                onOtpTextChange = {
                    authViewModel.setOtp(it)
                    if (it.length == 4) {
                        val authDto = AuthDto().apply {
                            phoneNo = phoneNumber.toString()
                            otp = it
                        }
                        authViewModel.verifyOTP(authDto)
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
                    fontFamily = Constant().balooda2font,
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