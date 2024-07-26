package com.akash.nou.dto

import androidx.compose.ui.Modifier

class NumericStepperDTO {
    lateinit var heading: String
    lateinit var rowModifier: Modifier
    var itemCount: Int = 0
    lateinit var decreaseNumber: () -> Unit
    lateinit var increaseNumber: () -> Unit
}