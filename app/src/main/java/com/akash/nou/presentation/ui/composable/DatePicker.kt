package com.akash.nou.view.feature.homepage.composable

import Constant
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.akash.nou.dto.PickerPopupDto
import com.akash.nou.presentation.viewmodel.HomeViewModel
import com.akash.nou.util.Converter
import java.util.Calendar
import java.util.TimeZone


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerPopUp(
    homeViewModel: HomeViewModel,
    pickerPopupDTO: PickerPopupDto,
) {
    val _selectedDate by homeViewModel.selectedDate.observeAsState(initial = pickerPopupDTO.heading)
    val openDialog = rememberSaveable { mutableStateOf(false) }
    ElevatedButton(
        enabled = true,
        colors = ButtonColors(
            containerColor = Constant.very_light_gray,
            contentColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth(0.43f)
            .background(Constant.small_light_gray, RoundedCornerShape(12.dp))
            .border(
                shape = RoundedCornerShape(12.dp),
                width = 1.dp,
                color = Constant.very_light_gray
            ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 16.dp,
            pressedElevation = 6.dp,
            disabledElevation = 0.dp
        ),
        border = BorderStroke(color = Color.Transparent, width = 1.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = { openDialog.value = true },
        contentPadding = ButtonDefaults.TextButtonWithIconContentPadding
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = pickerPopupDTO.leadingIcon),
                contentDescription = "date_icon"
            )
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = _selectedDate,
                style = TextStyle(
                    fontFamily = Constant.balooda2font,
                    fontSize = 14.sp,
                    color = Color.Black,
                ),
            )
        }
    }
    if (openDialog.value) {
        val today = Calendar.getInstance(TimeZone.getTimeZone("Asia/Dhaka"))
        val datePickerState = rememberDatePickerState(
            yearRange = today.get(Calendar.YEAR)..(today.get(Calendar.YEAR)),
            initialSelectedDateMillis = today.timeInMillis,
            initialDisplayedMonthMillis = today.timeInMillis,
            initialDisplayMode = DisplayMode.Picker
        )
        val confirmEnabled = derivedStateOf {
            datePickerState.selectedDateMillis?.let {
                it >= today.timeInMillis
            } ?: false
        }

        DatePickerDialog(
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                decorFitsSystemWindows = true
            ),
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    colors = ButtonColors(
                        contentColor = Color.Black,
                        containerColor = Color.Transparent,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.Transparent
                    ),
                    onClick = {
                        openDialog.value = false
                        var date = pickerPopupDTO.heading
                        if (datePickerState.selectedDateMillis != null) {
                            date = Converter.longToTime(datePickerState.selectedDateMillis!!)
                        }
                        homeViewModel.setDate("$date")
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text(
                        text = "ঠিক আছে",
                        fontFamily = Constant.balooda2font,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(
                        text = "এখন না",
                        fontFamily = Constant.balooda2font,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}