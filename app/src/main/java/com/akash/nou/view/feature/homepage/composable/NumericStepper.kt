/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.homepage.composable

import Constant
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.dto.NumericStepperDto

@Composable
fun NumericStepper(numericStepperDTO: NumericStepperDto) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(start = 5.dp, bottom = 8.dp),
            text = numericStepperDTO.heading,
            style = TextStyle(
                fontFamily = Constant().balooda2font,
                fontSize = 14.sp,
                color = Color.Black,
            ),
        )
        Row(
            modifier = numericStepperDTO.rowModifier,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                content = {
                    Text(
                        text = "-",
                        style = TextStyle(
                            fontFamily = Constant().balooda2font,
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 16.dp,
                    pressedElevation = 6.dp,
                    disabledElevation = 0.dp
                ),
                colors = ButtonColors(
                    containerColor = Constant().app_theme_color,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                ),
                enabled = numericStepperDTO.itemCount != 0,
                modifier = Modifier
                    .background(
                        color = Constant().app_theme_color,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .width(50.dp)
                    .height(50.dp),
                border = BorderStroke(
                    color = Color.Transparent,
                    width = 1.dp
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = numericStepperDTO.decreaseNumber,
                contentPadding = ButtonDefaults.TextButtonWithIconContentPadding
            )

            Text(
                modifier = Modifier.width(10.dp),
                text = numericStepperDTO.itemCount.toString(),
                style = TextStyle(
                    fontFamily = Constant().balooda2font,
                    fontSize = 20.sp,
                    color = Color.Black,
                ),
            )

            Button(
                content = {
                    Text(
                        text = "+",
                        style = TextStyle(
                            fontFamily = Constant().balooda2font,
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 16.dp,
                    pressedElevation = 6.dp,
                    disabledElevation = 0.dp
                ),
                colors = ButtonColors(
                    containerColor = Constant().app_theme_color,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                ),
                enabled = numericStepperDTO.itemCount != 5,
                modifier = Modifier
                    .background(
                        color = Constant().app_theme_color,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .width(50.dp)
                    .height(50.dp),
                border = BorderStroke(
                    color = Color.Transparent,
                    width = 1.dp
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = numericStepperDTO.increaseNumber,
                contentPadding = ButtonDefaults.TextButtonWithIconContentPadding
            )
        }
    }
}