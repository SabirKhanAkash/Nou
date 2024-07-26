/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.auth.composable

import Constant
import android.content.Context
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.R
import com.akash.nou.viewmodel.AuthViewModel
import showTopToast

@Composable
fun AuthActivityContent(applicationContext: Context, authViewModel: AuthViewModel) {
    val phoneNumber by authViewModel.phoneNumber.observeAsState(initial = "")
    val isPhoneNumberValid by authViewModel.isPhoneNumberValid.observeAsState(initial = true)
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
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
                modifier = Modifier.padding(bottom = 16.dp),
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

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = phoneNumber,
                onValueChange = { newValue ->
                    val filteredValue = newValue.filter { it.isDigit() }.take(11)
                    authViewModel.setPhoneNumber(filteredValue)
                    if (filteredValue.length == 11) {
                        keyboardController?.hide() // Hide keyboard when length is 11
                    }
                },
                isError = !isPhoneNumberValid,
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = Constant().balooda2font,
                    color = Color.Black
                ),
                label = { Text("ফোন নম্বর লিখুন", color = Constant().regular_gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Constant().app_theme_color,
                    cursorColor = Color.Black,
                    textColor = Color.DarkGray,
                    placeholderColor = Color.LightGray
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 44.dp, bottom = 44.dp),
            ) {
                OutlinedButton(
                    onClick = {
                        if (phoneNumber.length == 11 && phoneNumber.startsWith("01")) {
                            authViewModel.verifyPhoneNumber(phoneNumber)
                        }
                        else {
                            showTopToast(
                                applicationContext,
                                "১১ ডিজিটের একটি বৈধ নম্বর দিন",
                                "short",
                                "negative"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Constant().app_theme_color)
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
                            fontFamily = Constant().balooda2font,
                            color = Color.White,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.check_circle_icon),
                            contentDescription = "tick icon",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }

                }
            }

        }
    }

}