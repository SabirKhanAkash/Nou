package com.akash.nou.view.feature.homepage.composable

import Constant
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.dto.SeatBookingPopUpDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeatBookingPopUp(context: Context, seatBookingPopUpDTO: SeatBookingPopUpDTO) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ModalBottomSheet(onDismissRequest = { seatBookingPopUpDTO.isSeatViewPoppedUp = false }) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                /// 2 MINUTES COUNTDOWN WARNING TEXT
                Text(
                    text = "🕘 02 মি : 00 সে এর মধ্যে কাজ শেষ করুন",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Constant().toast_red,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Constant().toast_text_red
                )
                Spacer(modifier = Modifier.height(10.dp))

                TicketSummary(seatBookingPopUpDTO)
                Spacer(modifier = Modifier.height(10.dp))

                SeatLegend()
                Spacer(modifier = Modifier.height(10.dp))

                SeatPlanView(context, seatBookingPopUpDTO)
                Spacer(modifier = Modifier.height(10.dp))

                ConfirmPurchaseButton(seatBookingPopUpDTO)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}


