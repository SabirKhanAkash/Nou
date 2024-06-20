package com.akash.nou.view.feature.homepage.composable

import Constant
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.utils.Tools


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerPopUp(heading: String, leadingIcon: Int) {
    var dateResult by remember { mutableStateOf(heading) }
    val openDialog = remember { mutableStateOf(false) }
    OutlinedButton(
        modifier = Modifier
            .width(135.dp)
            .height(55.dp)
            .background(Constant().small_light_gray, RoundedCornerShape(10.dp)),
        onClick = { openDialog.value = true },
        shape = RoundedCornerShape(10.dp),
        contentPadding = ButtonDefaults.TextButtonWithIconContentPadding
    ) {
        Text(
            dateResult,
            fontFamily = Constant().balooda2font,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
    if (openDialog.value) {
        val datePickerState = rememberDatePickerState()
        val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
        val confirmEnabled = derivedStateOf { datePickerState.displayMode != null }

        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        var date = "no selection"
                        if (datePickerState.selectedDateMillis != null) {
                            date = Tools.convertLongToTime(datePickerState.selectedDateMillis!!)
                        }
                        dateResult = date
                        Log.d("tag", "onConfirm: $date")
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        Log.d("tag", "onDismiss: ${openDialog.value}")
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

}