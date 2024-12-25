/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.presentation.screen

import Constant
import CustomToast
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.akash.nou.presentation.ui.composable.LoaderDialog
import com.akash.nou.presentation.viewmodel.AuthViewModel
import com.akash.nou.service.EncryptedSharedPrefService
import com.akash.nou.service.SharedPrefService
import com.akash.nou.util.ApiState
import com.plcoding.mvvmtodoapp.util.UiEvent

@Composable
fun AuthScreen(
    authViewModel: AuthViewModel,
    navController: NavHostController,
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    /**
     * Variables Initialization Part
     */
    val applicationContext = LocalContext.current
    val sharedPrefService = SharedPrefService.getInstance(applicationContext)
    val encryptedSharedPrefService = EncryptedSharedPrefService.getInstance(applicationContext)
    val authResponse = authViewModel.loginState.collectAsState().value
    val phoneNumber by authViewModel.phoneNumber.observeAsState(initial = "")
    val isPhoneNumberValid by authViewModel.isPhoneNumberValid.observeAsState(initial = true)
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    val loginState by authViewModel.loginState.collectAsState()
    val isLoading by authViewModel.isLoading.observeAsState(initial = false)
    LoaderDialog(isLoading = isLoading)

    /**
     * Variable Value updating based on Api Responses
     */
//    LaunchedEffect(key1 = true) {
//        authViewModel.uiEvent.collect { event ->
//            when (event) {
//                is UiEvent.Navigate -> onNavigate(event)
//                else -> Unit
//            }
//        }
//    }

    LaunchedEffect(key1 = loginState) {
        if (loginState is ApiState.Success) {
            val resultData = (loginState as ApiState.Success).data
            if (resultData.status == "Success") {
                navController.navigate("otp_screen")
            }
            else {
                CustomToast(
                    applicationContext, "দুঃখিত! আপনি নিবন্ধিত ব্যবহারকারী না", "short",
                    "negative"
                )
            }
        }
        if (loginState is ApiState.Error) {
            authViewModel.dismissLoading()
            Log.d(Constant.LOG_TAG, "${(loginState as ApiState.Error).errorMsg}")
            CustomToast(
                applicationContext, "দুঃখিত! কারিগরি ত্রুটি হয়েছে ☹️ ${
                    (loginState as ApiState
                    .Error).errorMsg
                }",
                "short",
                "neutral"
            )
        }
        if (loginState is ApiState.Loading) {
            if (isLoading) {
                authViewModel.startLoading()
            }
        }
    }

    /**
     * UI Part of Auth Screen
     */
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 24.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_nou),
                contentDescription = Constant.LOGIN_SCREEN_ICON_DESCRIPTION,
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
                    fontFamily = Constant.balooda2font,
                    fontSize = 40.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Left
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = Constant.LOGIN_SCREEN_TAGLINE,
                style = TextStyle(
                    fontFamily = Constant.balooda2font,
                    fontSize = 14.sp,
                    color = Constant.light_gray_2,
                    textAlign = TextAlign.Left
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = phoneNumber,
                onValueChange = { newValue ->
                    val filteredValue = newValue.filter { it.isDigit() }.take(11)
                    authViewModel.setPhoneNumber(filteredValue)
//                    authViewModel.onEvent(AuthEvent.OnLogin(phoneNumber))
                    if (filteredValue.length == 11) keyboardController?.hide()
                },
                isError = !isPhoneNumberValid,
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = Constant.balooda2font,
                    color = Color.Black
                ),
                label = { Text("ফোন নম্বর লিখুন", color = Constant.regular_gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 44.dp, bottom = 44.dp),
            ) {
                OutlinedButton(
                    onClick = {
                        if (phoneNumber.length == 11 && phoneNumber.startsWith("01")) {
                            authViewModel.login(
                                AuthRequest(
                                    authSource = Constant.AUTH_SOURCE,
                                    phoneNo =
                                    phoneNumber,
                                ),
                                sharedPrefService,
                                encryptedSharedPrefService
                            )
                            authViewModel.startLoading()
                        }
                        else {
                            CustomToast(
                                applicationContext,
                                "১১ ডিজিটের একটি বৈধ নম্বর দিন",
                                "short",
                                "negative"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Constant.app_theme_color)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 4.dp),

                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = "লগইন করুন",
                            fontFamily = Constant.balooda2font,
                            color = Color.White,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_check_circle),
                            contentDescription = "tick icon",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }

                }
                when (authResponse) {
                    is ApiState.Success -> {
                        LoaderDialog(false)
                    }

                    is ApiState.Error -> {
                        LoaderDialog(false)
                    }

                    is ApiState.Loading -> {
                        if (isLoading) {
                            LoaderDialog(true)
                        }
                    }
                }
            }

        }
    }
}