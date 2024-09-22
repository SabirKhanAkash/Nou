package com.akash.nou.utils

import java.util.Calendar

fun getCurrentTimeAsNumber(): Int {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    return String.format("%d%02d", hour, minute).toInt()
}