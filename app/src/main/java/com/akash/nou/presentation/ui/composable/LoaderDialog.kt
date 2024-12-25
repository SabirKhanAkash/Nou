package com.akash.nou.presentation.ui.composable

import Constant
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun LoaderDialog(isLoading: Boolean) {
    if (isLoading) {
        Dialog(onDismissRequest = { /* Prevent dialog dismissal */ }) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    color = Constant.app_theme_color, // Replace with your theme color
                    strokeWidth = 5.dp
                )
            }
        }
    }
}