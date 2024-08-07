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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akash.nou.R
import com.akash.nou.dto.DropDownMenuDTO
import com.akash.nou.dto.NumericStepperDTO
import com.akash.nou.dto.PickerPopupDTO
import com.akash.nou.dto.SeatBookingPopUpDTO
import com.akash.nou.dto.TicketLookUpDTO
import com.akash.nou.viewmodel.TicketViewModel
import com.akash.nou.viewmodelfactory.TicketViewModelFactory
import showTopToast

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun TicketScreen(
    context: Context,
    ticketViewModel: TicketViewModel = viewModel(factory = TicketViewModelFactory())
) {
    val _passengerCount by ticketViewModel.passengerCount.observeAsState(initial = 0)
    val _childPassengerCount by ticketViewModel.childPassengerCount.observeAsState(initial = 0)
    val _selectedSeatType by ticketViewModel.selectedSeatType.observeAsState(initial = "")
    val _selectedSource by ticketViewModel.selectedSource.observeAsState(initial = "")
    val _selectedDestination by ticketViewModel.selectedDestination.observeAsState(initial = "")
    val _selectedDate by ticketViewModel.selectedDate.observeAsState(initial = "যাত্রার তারিখ")
    val _selectedTime by ticketViewModel.selectedTime.observeAsState(initial = "যাত্রার সময়")
    val _isSeatViewPoppedUp by ticketViewModel.isSeatViewPoppedUp.observeAsState(initial = false)
//    val _seats by ticketViewModel.seats.observeAsState(initial = emptyList())
    val _numberOfColumns by ticketViewModel.numberOfColumns.observeAsState(initial = 6)

    val seatViewScaffoldState = rememberBottomSheetScaffoldState()
    var _seats by rememberSaveable { mutableStateOf<List<TicketLookUpDTO>>(emptyList()) }

    BottomSheetScaffold(
        scaffoldState = seatViewScaffoldState,
        sheetContent = {
            if (_isSeatViewPoppedUp) {
                val ticketLookUpDTO = TicketLookUpDTO().apply {
                    seatType = _selectedSeatType
                    source = _selectedSource
                    destination = _selectedDestination
                    date = _selectedDate
                    time = _selectedTime
                    passengerCount = _passengerCount
                    childPassengerCount = _childPassengerCount
                }
                _seats = listOf(
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
                )

                val seatBookingPopUpDTO = SeatBookingPopUpDTO().apply {
                    isSeatViewPoppedUp = _isSeatViewPoppedUp
                    selectedDate = _selectedDate
                    selectedTime = _selectedTime
                    selectedSeatType = _selectedSeatType
                    selectedDestination = _selectedDestination
                    selectedSource = _selectedSource
                    childPassengerCount = _childPassengerCount
                    passengerCount = _passengerCount
                    seats = _seats
                    numberOfColumns = _numberOfColumns
                }
                SeatBookingPopUp(context, seatBookingPopUpDTO)
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
                            DropDownMenu("seatType", dropDownMenuDTOForSeatType)

                            Spacer(modifier = Modifier.height(25.dp))

                            val dropDownMenuDTOForSource = DropDownMenuDTO().apply {
                                heading = "যাত্রা শুরুর স্থান"
                                leadingIcon = R.drawable.source_icon
                                items = R.array.zilla
                            }
                            DropDownMenu("source", dropDownMenuDTOForSource)

                            Spacer(modifier = Modifier.height(25.dp))

                            val dropDownMenuDTOForDestination = DropDownMenuDTO().apply {
                                heading = "যাত্রা শেষের স্থান"
                                leadingIcon = R.drawable.destination_icon
                                items = R.array.zilla
                            }
                            DropDownMenu("destination", dropDownMenuDTOForDestination)

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
                                    DatePickerPopUp(pickerPopupDTO)
                                }
                                Box(
                                    modifier = Modifier.padding(start = 10.dp)
                                ) {
                                    val pickerPopupDTO = PickerPopupDTO().apply {
                                        heading = "যাত্রার সময়"
                                        leadingIcon = R.drawable.time_icon
                                    }
                                    TimePickerPopUp(pickerPopupDTO)
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
                                    itemCount = _passengerCount
                                    decreaseNumber = {
                                        if (_passengerCount > 0) ticketViewModel
                                            .decrementPassengerCount()
                                        if (_passengerCount == 0) ticketViewModel
                                            .initChildPassengerCount()
                                    }
                                    increaseNumber = {
                                        if (_passengerCount < 5) ticketViewModel
                                            .incrementPassengerCount()
                                    }
                                }
                                NumericStepper(numericStepperDTOForAdult)

                                val numericStepperDTOForChild = NumericStepperDTO().apply {
                                    heading = "যাত্রী সংখ্যা (শিশু)"
                                    rowModifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp)
                                    itemCount = _childPassengerCount
                                    decreaseNumber = {
                                        if (_childPassengerCount > 0) ticketViewModel
                                            .decrementChildPassengerCount()
                                    }
                                    increaseNumber = {
                                        if (_childPassengerCount < 5 && _passengerCount > 0) ticketViewModel
                                            .incrementChildPassengerCount()
                                        if (_passengerCount == 0) showTopToast(
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
                                    when (_selectedSeatType) {
                                        "" -> {
                                            ticketViewModel.dismissSeatViewPopUp()
                                            showTopToast(
                                                context,
                                                "Please select a seat type",
                                                "short",
                                                "neutral"
                                            )
                                        }

                                        else -> {
                                            when (_selectedSource) {
                                                "" -> {
                                                    ticketViewModel.dismissSeatViewPopUp()
                                                    showTopToast(
                                                        context,
                                                        "Please select a source location",
                                                        "short",
                                                        "neutral"
                                                    )
                                                }

                                                else -> {
                                                    when (_selectedDestination) {
                                                        "" -> {
                                                            ticketViewModel.dismissSeatViewPopUp()
                                                            showTopToast(
                                                                context,
                                                                "Please select a destination" + " location",
                                                                "short",
                                                                "neutral"
                                                            )
                                                        }

                                                        else -> {
                                                            when (_selectedDate) {
                                                                "যাত্রার তারিখ" -> {
                                                                    ticketViewModel.dismissSeatViewPopUp()
                                                                    showTopToast(
                                                                        context,
                                                                        "Please select a date",
                                                                        "short",
                                                                        "neutral"
                                                                    )
                                                                }

                                                                else -> {
                                                                    when (_selectedTime) {
                                                                        "যাত্রার সময়" -> {
                                                                            ticketViewModel.dismissSeatViewPopUp()
                                                                            showTopToast(
                                                                                context,
                                                                                "Please " + "select a time",
                                                                                "short",
                                                                                "neutral"
                                                                            )
                                                                        }

                                                                        else -> {
                                                                            when (_passengerCount) {
                                                                                0 -> {
                                                                                    ticketViewModel.dismissSeatViewPopUp()
                                                                                    showTopToast(
                                                                                        context,
                                                                                        "Please add a passenger",
                                                                                        "short",
                                                                                        "neutral"
                                                                                    )
                                                                                }

                                                                                else -> {
                                                                                    ticketViewModel.popUpSeatView()
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
