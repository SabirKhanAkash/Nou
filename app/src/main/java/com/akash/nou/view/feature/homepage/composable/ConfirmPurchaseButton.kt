package com.akash.nou.view.feature.homepage.composable

import Constant
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.dto.SeatBookingPopUpDTO

@Composable
fun ConfirmPurchaseButton(seatBookingPopUpDTO: SeatBookingPopUpDTO) {
    Button(
        colors = ButtonColors(
            containerColor = Constant().app_theme_color,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            color = Color.Transparent, width = 1.dp
        ),
        modifier = Modifier
            .fillMaxWidth(),

        onClick = { seatBookingPopUpDTO.isSeatViewPoppedUp = false }
    ) {
        Text(
            text = "টিকিট নিশ্চিত করুন",
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )
    }
}