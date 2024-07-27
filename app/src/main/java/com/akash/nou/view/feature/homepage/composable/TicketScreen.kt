/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.view.feature.homepage.composable

import Constant
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akash.nou.R
import com.akash.nou.dto.DropDownMenuDTO
import com.akash.nou.dto.NumericStepperDTO
import com.akash.nou.dto.PickerPopupDTO
import com.akash.nou.dto.TicketLookUpDTO
import showTopToast

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun TicketScreen(context: Context) {
    var passengerCount by rememberSaveable {
        mutableIntStateOf(0)
    }
    var childPassengerCount by rememberSaveable {
        mutableIntStateOf(0)
    }
    var selectedSeatType by rememberSaveable {
        mutableStateOf("")
    }
    var selectedSource by rememberSaveable {
        mutableStateOf("")
    }
    var selectedDestination by rememberSaveable {
        mutableStateOf("")
    }
    var selectedDate by rememberSaveable {
        mutableStateOf("")
    }
    var selectedTime by rememberSaveable {
        mutableStateOf("")
    }

    var isSeatViewPoppedUp by rememberSaveable {
        mutableStateOf(false)
    }

    var seats by rememberSaveable { mutableStateOf<List<TicketLookUpDTO>>(emptyList()) }

    val seatViewScaffoldState = rememberBottomSheetScaffoldState()

    val numberOfColumns = 6

    BottomSheetScaffold(
        scaffoldState = seatViewScaffoldState,
        sheetContent = {
            if (isSeatViewPoppedUp) {
                val ticketLookUpDTO = TicketLookUpDTO().apply {
                    seatType = selectedSeatType
                    source = selectedSource
                    destination = selectedDestination
                    date = selectedDate
                    time = selectedTime
                    passengerCount = passengerCount
                    childPassengerCount = childPassengerCount
                }
                seats = listOf(
                    TicketLookUpDTO().apply {
                        seatNumber = "A1"; isOccupied = false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "A2"; isOccupied = true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "A3"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "A4"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "A5"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "A6"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "B1"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "B2"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "B3"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "B4"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "B5"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "B6"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "C1"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "C2"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "C3"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "C4"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "C5"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "C6"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "D1"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "D2"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "D3"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "D4"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "D5"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "D6"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "E1"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "E2"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "E3"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "E4"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "E5"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "E6"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "F1"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "F2"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "F3"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "F4"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "F5"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "F6"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "G1"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "G2"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "G3"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "G4"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "G5"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "G6"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "H1"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "H2"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "H3"; isOccupied =
                        true; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "H4"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "H5"; isOccupied =
                        false; isSelected = false
                    },
                    TicketLookUpDTO().apply {
                        seatNumber = "H6"; isOccupied =
                        true; isSelected = false
                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "I1"; isOccupied =
//                        true; isSelected = false
//                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "I2"; isOccupied =
//                        false; isSelected = false
//                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "I3"; isOccupied =
//                        true; isSelected = false
//                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "I4"; isOccupied =
//                        false; isSelected = false
//                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "I5"; isOccupied =
//                        true; isSelected = false
//                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "I6"; isOccupied =
//                        false; isSelected = false
//                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "J1"; isOccupied =
//                        true; isSelected = false
//                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "J2"; isOccupied =
//                        false; isSelected = false
//                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "J3"; isOccupied =
//                        true; isSelected = false
//                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "J4"; isOccupied =
//                        false; isSelected = false
//                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "J5"; isOccupied =
//                        true; isSelected = false
//                    },
//                    TicketLookUpDTO().apply {
//                        seatNumber = "J6"; isOccupied =
//                        false; isSelected = false
//                    }
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ModalBottomSheet(onDismissRequest = { isSeatViewPoppedUp = false }) {
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                        ) {
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
                                                text = selectedDate,
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
                                                selectedTime,
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
                                            text = when (childPassengerCount) {
                                                0 -> {
                                                    "যাত্রী $passengerCount জন"
                                                }

                                                else -> {
                                                    "যাত্রী $passengerCount জন,\nশিশু " +
                                                            "$childPassengerCount জন"
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
                                            text = selectedSource,
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
                                            text = selectedDestination,
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
                                            selectedSeatType,
                                            color = Constant().medium_gray,
                                            maxLines = 3,
                                            overflow = TextOverflow.Ellipsis,
                                            textAlign = TextAlign.Center,
                                            fontSize = 15.sp
                                        )
                                    }

                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))

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
                                        color = Color.White,
                                        shape = RoundedCornerShape(6.dp)
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(color = Color.Transparent)
                                        .weight(1.0f, fill = true)
                                        .padding(
                                            vertical = 5.dp,
                                            horizontal = 16.dp
                                        )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(0.30f, fill = true),
                                        horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Image(
                                            colorFilter = ColorFilter.tint(color = Color.Gray),
                                            painter = painterResource(id = R.drawable.ic_chair),
                                            contentDescription = "Seat icon",
                                            modifier = Modifier
                                                .padding(2.dp)
                                        )
                                        Text(
                                            text = "ফাকা সীট",
                                            color = Constant().medium_gray,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            textAlign = TextAlign.Center,
                                            fontSize = 14.sp
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(0.35f, fill = true),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Image(
                                            colorFilter = ColorFilter.tint(color = Color.Green),
                                            painter = painterResource(id = R.drawable.ic_chair),
                                            contentDescription = "Seat icon",
                                            modifier = Modifier
                                                .padding(2.dp)
                                        )
                                        Text(
                                            text = "নির্বাচিত সীট",
                                            color = Constant().medium_gray,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            textAlign = TextAlign.Center,
                                            fontSize = 14.sp
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(0.35f, fill = true),
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Image(
                                            colorFilter = ColorFilter.tint(color = Constant().app_theme_color),
                                            painter = painterResource(id = R.drawable.ic_chair),
                                            contentDescription = "Seat icon",
                                            modifier = Modifier
                                                .padding(2.dp)
                                        )
                                        Text(
                                            text = "বিক্রিত সীট",
                                            color = Constant().medium_gray,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            textAlign = TextAlign.Center,
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            LazyVerticalGrid(
                                userScrollEnabled = true,
                                columns = GridCells.Fixed(numberOfColumns),
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
                                items(seats) { seat ->
                                    val imageColor = when {
                                        seat.isSelected -> Color.Green
                                        seat.isOccupied -> Constant().app_theme_color
                                        else -> Color.Gray
                                    }
                                    Column(
                                        modifier = Modifier
                                            .width(IntrinsicSize.Min)
                                            .height(IntrinsicSize.Min)
                                            .clickable {
                                                if (!seat.isOccupied) {
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
                                            text = "${seat.seatNumber}",
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
                            Spacer(modifier = Modifier.height(10.dp))

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

                                onClick = { isSeatViewPoppedUp = false }
                            ) {
                                Text(
                                    text = "টিকিট নিশ্চিত করুন",
                                    fontSize = 16.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }
            }
        }) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Constant().app_theme_color),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.global_map_icon),
                        contentDescription = "map_icon"
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 35.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = "টিকিট কাউন্টার",
                        style = TextStyle(
                            fontFamily = Constant().balooda2font,
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    )
                    Text(
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = "সিট বুক করুন",
                        style = TextStyle(
                            fontFamily = Constant().balooda2font,
                            color = Color.White,
                            fontSize = 18.sp,
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Surface(
                        shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                        modifier = Modifier.fillMaxSize(),
                        color = Color.White
                    ) {
                        /// DROPDOWN MENUS FOR SEAT-TYPE, SOURCE & DESTINATION
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 15.dp, horizontal = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(25.dp))

                            val dropDownMenuDTOForSeatType = DropDownMenuDTO().apply {
                                heading = "সিটের ধরণ"
                                leadingIcon = R.drawable.seat_icon
                                items = R.array.seat_category
                            }
                            selectedSeatType = DropDownMenu(dropDownMenuDTOForSeatType)

                            Spacer(modifier = Modifier.height(25.dp))

                            val dropDownMenuDTOForSource = DropDownMenuDTO().apply {
                                heading = "যাত্রা শুরুর স্থান"
                                leadingIcon = R.drawable.source_icon
                                items = R.array.zilla
                            }
                            selectedSource = DropDownMenu(dropDownMenuDTOForSource)

                            Spacer(modifier = Modifier.height(25.dp))

                            val dropDownMenuDTOForDestination = DropDownMenuDTO().apply {
                                heading = "যাত্রা শেষের স্থান"
                                leadingIcon = R.drawable.destination_icon
                                items = R.array.zilla
                            }
                            selectedDestination = DropDownMenu(dropDownMenuDTOForDestination)

                            Spacer(modifier = Modifier.height(30.dp))

                            /// DATE TIME PICKER
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(
                                    modifier = Modifier.padding(end = 10.dp)
                                ) {
                                    val pickerPopupDTO = PickerPopupDTO().apply {
                                        heading = "যাত্রার তারিখ"
                                        leadingIcon = R.drawable.calendar_icon
                                    }
                                    selectedDate = DatePickerPopUp(pickerPopupDTO)
                                }
                                Box(
                                    modifier = Modifier.padding(start = 10.dp)
                                ) {
                                    val pickerPopupDTO = PickerPopupDTO().apply {
                                        heading = "যাত্রার সময়"
                                        leadingIcon = R.drawable.time_icon
                                    }
                                    selectedTime = TimePickerPopUp(pickerPopupDTO)
                                }
                            }

                            Spacer(modifier = Modifier.height(30.dp))

                            /// NUMERIC STEPPER BUTTONS FOR ADULT & CHILD
                            Row(
                                modifier = Modifier.fillMaxWidth(1.0f),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                val numericStepperDTOForAdult = NumericStepperDTO().apply {
                                    heading = "যাত্রী সংখ্যা (প্রাপ্তবয়স্ক)"
                                    rowModifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .padding(end = 10.dp)
                                    itemCount = passengerCount
                                    decreaseNumber = {
                                        if (passengerCount > 0) passengerCount -= 1
                                        if (passengerCount == 0) childPassengerCount = 0
                                    }
                                    increaseNumber = {
                                        if (passengerCount < 5) passengerCount += 1
                                    }
                                }
                                NumericStepper(numericStepperDTOForAdult)

                                val numericStepperDTOForChild = NumericStepperDTO().apply {
                                    heading = "যাত্রী সংখ্যা (শিশু)"
                                    rowModifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp)
                                    itemCount = childPassengerCount
                                    decreaseNumber = {
                                        if (childPassengerCount > 0) childPassengerCount -= 1
                                    }
                                    increaseNumber = {
                                        if (childPassengerCount < 5 && passengerCount > 0) childPassengerCount += 1
                                        if (passengerCount == 0) showTopToast(
                                            context,
                                            "অন্তত একজন প্রাপ্তবয়স্ক যাত্রী প্রয়োজন",
                                            "short",
                                            "neutral"
                                        )
                                    }
                                }
                                NumericStepper(numericStepperDTOForChild)
                            }

                            Spacer(modifier = Modifier.height(30.dp))

                            /// TICKET SEARCH BUTTON
                            ElevatedButton(
                                border = BorderStroke(
                                    color = Color.Transparent, width = 1.dp
                                ),
                                colors = ButtonColors(
                                    containerColor = Constant().app_theme_color,
                                    contentColor = Color.White,
                                    disabledContainerColor = Color.Gray,
                                    disabledContentColor = Color.White
                                ),
                                content = {
                                    Text(
                                        text = "টিকিট খুঁজুন",
                                        style = TextStyle(
                                            fontFamily = Constant().balooda2font,
                                            fontSize = 15.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )
                                },
                                contentPadding = ButtonDefaults.TextButtonWithIconContentPadding,
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 16.dp,
                                    pressedElevation = 6.dp,
                                    disabledElevation = 0.dp
                                ),
                                enabled = true,
                                modifier = Modifier
                                    .fillMaxWidth(),

                                onClick = {
                                    when (selectedSeatType) {
                                        "" -> {
                                            isSeatViewPoppedUp = false
                                            showTopToast(
                                                context,
                                                "Please select a seat type",
                                                "short",
                                                "neutral"
                                            )
                                        }

                                        else -> {
                                            when (selectedSource) {
                                                "" -> {
                                                    isSeatViewPoppedUp = false
                                                    showTopToast(
                                                        context,
                                                        "Please select a source location",
                                                        "short",
                                                        "neutral"
                                                    )
                                                }

                                                else -> {
                                                    when (selectedDestination) {
                                                        "" -> {
                                                            isSeatViewPoppedUp = false
                                                            showTopToast(
                                                                context,
                                                                "Please select a destination" + " location",
                                                                "short",
                                                                "neutral"
                                                            )
                                                        }

                                                        else -> {
                                                            when (selectedDate) {
                                                                "যাত্রার তারিখ" -> {
                                                                    isSeatViewPoppedUp = false
                                                                    showTopToast(
                                                                        context,
                                                                        "Please select a date",
                                                                        "short",
                                                                        "neutral"
                                                                    )
                                                                }

                                                                else -> {
                                                                    when (selectedTime) {
                                                                        "যাত্রার সময়" -> {
                                                                            isSeatViewPoppedUp =
                                                                                false
                                                                            showTopToast(
                                                                                context,
                                                                                "Please " + "select a time",
                                                                                "short",
                                                                                "neutral"
                                                                            )
                                                                        }

                                                                        else -> {
                                                                            when (passengerCount) {
                                                                                0 -> {
                                                                                    isSeatViewPoppedUp =
                                                                                        false
                                                                                    showTopToast(
                                                                                        context,
                                                                                        "Please add a passenger",
                                                                                        "short",
                                                                                        "neutral"
                                                                                    )
                                                                                }

                                                                                else -> {
                                                                                    isSeatViewPoppedUp =
                                                                                        true
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                },
                                shape = RoundedCornerShape(12.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun TicketScreenPreview() {
    TicketScreenPreviewContent()
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun TicketScreenPreviewContent() {
    val context = androidx.compose.ui.platform.LocalContext.current
    TicketScreen(context)
}
