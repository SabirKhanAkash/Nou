package com.akash.nou.view.feature.homepage.composable

import Constant
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.R
import com.akash.nou.dto.SeatBookingPopUpDto

@Composable
fun TicketSummary(seatBookingPopUpDTO: SeatBookingPopUpDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(6.dp)
            )
            .border(
                width = 1.dp,
                color = Constant().app_theme_color,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(
                    weight = 1.0f,
                    fill = true
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(0.5f, fill = true)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(6.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_calender),
                        contentDescription = "Date icon",
                        modifier = Modifier
                            .padding(5.dp)
                            .size(15.dp)
                    )
                    Text(
                        text = seatBookingPopUpDTO.selectedDate,
                        color = Constant().medium_gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(6.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_clock),
                        contentDescription = "Time icon",
                        modifier = Modifier
                            .padding(5.dp)
                            .size(15.dp)
                    )
                    Text(
                        seatBookingPopUpDTO.selectedTime,
                        color = Constant().medium_gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(5.dp)
                    .weight(
                        weight = 0.5f,
                        fill = true
                    ),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Passenger icon",
                    modifier = Modifier
                        .padding(5.dp)
                        .size(15.dp)
                )
                Text(
                    text = when (seatBookingPopUpDTO.childPassengerCount) {
                        0 -> {
                            "যাত্রী ${seatBookingPopUpDTO.passengerCount} জন"
                        }

                        else -> {
                            "যাত্রী ${seatBookingPopUpDTO.passengerCount} জন,\nশিশু " +
                                    "${seatBookingPopUpDTO.childPassengerCount} জন"
                        }
                    },
                    color = Constant().medium_gray,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(
                    weight = 1.0f,
                    fill = true
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(5.dp)
                    .weight(
                        weight = 0.75f,
                        fill = true
                    ),
            ) {
                Text(
                    text = seatBookingPopUpDTO.selectedSource,
                    color = Constant().medium_gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = "arrow icon",
                    modifier = Modifier
                        .padding(5.dp)
                )
                Text(
                    text = seatBookingPopUpDTO.selectedDestination,
                    color = Constant().medium_gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp
                )
            }
            Spacer(modifier = Modifier.width(15.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(5.dp)
                    .weight(
                        weight = 0.25f,
                        fill = true
                    ),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.seat_icon),
                    contentDescription = "Seat icon",
                    modifier = Modifier
                        .padding(5.dp)
                        .size(20.dp)
                )
                Text(
                    seatBookingPopUpDTO.selectedSeatType,
                    color = Constant().medium_gray,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
            }

        }
    }
}