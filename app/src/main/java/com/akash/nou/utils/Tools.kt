package com.akash.nou.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Tools {
    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return format.format(date)
    }
}