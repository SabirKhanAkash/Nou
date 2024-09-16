package com.akash.nou.view.feature.homepage.composable

import Constant
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.R
import com.akash.nou.dto.SeatBookingPopUpDTO
import showTopToast

@Composable
fun SeatPlanView(context: Context, seatBookingPopUpDTO: SeatBookingPopUpDTO) {
    LazyVerticalGrid(
        userScrollEnabled = true,
        columns = GridCells.Fixed(seatBookingPopUpDTO.numberOfColumns),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(6.dp)
            )
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(6.dp)
            ),
    ) {
        items(seatBookingPopUpDTO.seats) { seat ->
            val imageColor = when {
                seat.isSelected -> Color.Green
                seat.sold -> Constant().app_theme_color
                else -> Color.Gray
            }
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
                    .clickable {
                        if (!seat.sold) {
                            seat.isSelected = !seat.isSelected
                            showTopToast(
                                context,
                                "আপনি আসন নং ${seat.seatNumber} নির্বাচন করেছেন",
                                "short",
                                "positive"
                            )
                        }
                        else {
                            showTopToast(
                                context,
                                "${seat.seatNumber} নং আসনটি ইতিমধ্যে " +
                                        "বিক্রি হয়ে " +
                                        "গেছে",
                                "short",
                                "neutral"
                            )
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    colorFilter = ColorFilter.tint(imageColor),
                    painter = painterResource(id = R.drawable.ic_chair),
                    contentDescription = "Seat ${seat.seatNumber}",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(horizontal = 4.dp, vertical = 2.dp)

                )
                Text(
                    text = seat.seatNumber,
                    color = Constant().medium_gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}