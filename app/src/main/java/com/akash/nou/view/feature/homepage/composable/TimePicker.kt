/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.homepage.composable

import Constant
import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.akash.nou.dto.PickerPopupDTO
import java.text.SimpleDateFormat
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerPopUp(pickerPopupDTO: PickerPopupDTO): String {
    var showTimePicker by rememberSaveable { mutableStateOf(false) }
    var finalTime by rememberSaveable { mutableStateOf(pickerPopupDTO.heading) }
    val cal = Calendar.getInstance()
    val currentHour = cal.get(Calendar.HOUR_OF_DAY)
    val currentMinute = cal.get(Calendar.MINUTE)
    val state = rememberTimePickerState(
        is24Hour = false,
        initialHour = currentHour,
        initialMinute = currentMinute
    )
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    ElevatedButton(
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 16.dp,
            pressedElevation = 6.dp,
            disabledElevation = 0.dp
        ),
        enabled = true,
        colors = ButtonColors(
            containerColor = Constant().very_light_gray,
            contentColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(Constant().small_light_gray, RoundedCornerShape(12.dp))
            .border(
                shape = RoundedCornerShape(12.dp), width = 1.dp, color = Constant().very_light_gray
            ),
        border = BorderStroke(color = Color.Transparent, width = 1.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = { showTimePicker = true },
        contentPadding = ButtonDefaults.TextButtonWithIconContentPadding
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = pickerPopupDTO.leadingIcon),
                contentDescription = "time_icon"
            )
            Text(
                color = Color.Black,
                modifier = Modifier.padding(start = 5.dp),
                text = finalTime,
                style = TextStyle(
                    fontFamily = Constant().balooda2font,
                    fontSize = 14.sp,
                    color = Color.Black,
                ),
            )
        }

    }
    if (showTimePicker) {
        TimePickerDialog(
            onCancel = { showTimePicker = false },
            onConfirm = {
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, state.hour)
                cal.set(Calendar.MINUTE, state.minute)
                cal.isLenient = false
                finalTime = "সময়: ${formatter.format(cal.time)}"
                showTimePicker = false
            },
        ) {
            TimeInput(
                colors = TimePickerColors(
                    selectorColor = Constant().app_theme_color,
                    containerColor = Color.White,
                    clockDialColor = Color.White,
                    clockDialSelectedContentColor = Constant().app_theme_color,
                    clockDialUnselectedContentColor = Color.White,
                    periodSelectorBorderColor = Color.Black,
                    periodSelectorSelectedContainerColor = Constant().app_theme_color,
                    periodSelectorSelectedContentColor = Color.White,
                    periodSelectorUnselectedContainerColor = Color.White,
                    periodSelectorUnselectedContentColor = Color.Black,
                    timeSelectorSelectedContainerColor = Color.White,
                    timeSelectorSelectedContentColor = Color.Black,
                    timeSelectorUnselectedContainerColor = Color.White,
                    timeSelectorUnselectedContentColor = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                state = state,
            )
        }
    }

    return finalTime
}

@Composable
fun TimePickerDialog(
    title: String = "যাত্রার সময়",
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = Color.White
                )
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onCancel) {
                        Text("এখন না", color = Color.Black)
                    }
                    TextButton(onClick = onConfirm) {
                        Text("ঠিক আছে", color = Color.Black)
                    }
                }
            }
        }
    }
}