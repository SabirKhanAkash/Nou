/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.presentation.screen

import AppRoute
import Constant
import CustomToast
import android.os.Build
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.akash.nou.R
import com.akash.nou.dto.AllLookUpDto
import com.akash.nou.dto.DropDownMenuDto
import com.akash.nou.dto.NumericStepperDto
import com.akash.nou.dto.PickerPopupDto
import com.akash.nou.dto.SeatBookingPopUpDto
import com.akash.nou.dto.TicketLookUpDto
import com.akash.nou.presentation.ui.composable.DropDownMenu
import com.akash.nou.presentation.ui.composable.SeatBookingPopUp
import com.akash.nou.presentation.viewmodel.HomeViewModel
import com.akash.nou.service.EncryptedSharedPrefService
import com.akash.nou.service.SharedPrefService
import com.akash.nou.util.ApiState
import com.akash.nou.util.Converter
import com.akash.nou.util.logOutUser
import com.akash.nou.view.feature.homepage.composable.DatePickerPopUp
import com.akash.nou.view.feature.homepage.composable.NumericStepper

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun TicketScreen(navController: NavController) {
    val applicationContext = LocalContext.current
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val sharedPrefService = SharedPrefService.getInstance(applicationContext)
    val encryptedSharedPrefService = EncryptedSharedPrefService.getInstance(applicationContext)
    val _passengerCount by homeViewModel.passengerCount.observeAsState(initial = 0)
    val _childPassengerCount by homeViewModel.childPassengerCount.observeAsState(initial = 0)
    val _selectedSeatType by homeViewModel.selectedSeatType.observeAsState(initial = "")
    val _selectedSource by homeViewModel.selectedSource.observeAsState(initial = "")
    val _selectedDestination by homeViewModel.selectedDestination.observeAsState(initial = "")
    val _selectedDate by homeViewModel.selectedDate.observeAsState(initial = "যাত্রার তারিখ")
    val _selectedTime by homeViewModel.selectedTime.observeAsState(initial = "যাত্রার সময়")
    val _isSeatViewPoppedUp by homeViewModel.isSeatViewPoppedUp.observeAsState(initial = false)
//    val _seats by homeViewModel.seats.observeAsState(initial = emptyList())
    val _numberOfColumns by homeViewModel.numberOfColumns.observeAsState(initial = 6)
    val seatCategories = remember { mutableStateListOf<String>() }
    val stations = remember { mutableStateListOf<String>() }
    val journeyTimes = remember { mutableStateListOf<String>() }
    val seatViewScaffoldState = rememberBottomSheetScaffoldState()
    var _seats by rememberSaveable { mutableStateOf<List<TicketLookUpDto>>(emptyList()) }
    val ticketLookUpState by homeViewModel.ticketLookUpState.collectAsState()
    val seatTypeLookUpState by homeViewModel.seatTypeLookUpState.collectAsState()
    val stationLookUpState by homeViewModel.stationLookUpState.collectAsState()
    val journeyTimeLookUpState by homeViewModel.journeyTimeLookUpState.collectAsState()
    val ticketLookUpDTO = TicketLookUpDto().apply {
        pageNo = 1
        seatCategory = _selectedSeatType
        source = _selectedSource
        destination = _selectedDestination
        date = _selectedDate
        time = _selectedTime
        passengerCount = _passengerCount
        childPassengerCount = _childPassengerCount
    }
    val seatTypeLookUpDto = AllLookUpDto().apply {
        pageNo = 1
        type = "seat_category"
        perPage = 20
        orderValue = 0
        vendorId = sharedPrefService.getUser("user")?._id
    }
    val stationLookUpDto = AllLookUpDto().apply {
        pageNo = 1
        type = "station"
        perPage = 64
        orderValue = 0
        vendorId = sharedPrefService.getUser("user")?._id
    }
    val journeyTimeLookUpDto = AllLookUpDto().apply {
        pageNo = 1
        type = "journey_time"
        perPage = 48
        orderValue = Converter.currentTimeToNumber()
        vendorId = sharedPrefService.getUser("user")?._id
    }

    homeViewModel.getAllLookUpInfos(
        seatTypeLookUpDto,
        sharedPrefService,
        encryptedSharedPrefService
    )
    homeViewModel.getAllLookUpInfos(
        stationLookUpDto,
        sharedPrefService,
        encryptedSharedPrefService
    )
    homeViewModel.getAllLookUpInfos(
        journeyTimeLookUpDto,
        sharedPrefService,
        encryptedSharedPrefService
    )

    LaunchedEffect(
        key1 = seatTypeLookUpState, key2 = stationLookUpState, key3 = journeyTimeLookUpState
    ) {
        when (seatTypeLookUpState) {
            is ApiState.Loading -> {}
            is ApiState.Success -> {
                val resultData = (seatTypeLookUpState as ApiState.Success).data
                if (resultData.status == "Success") {
                    val newSeatCategories =
                        resultData.data?.allLookUpList?.filter { it.type == "seat_category" }
                            ?.mapNotNull { it.title_bn } ?: emptyList()
                    Log.d(Constant.LOG_TAG, "${newSeatCategories.size}")
                    seatCategories.clear()
                    seatCategories.addAll(newSeatCategories)
                    CustomToast(
                        applicationContext, "seat types fetched successfully", "short", "positive"
                    )
                }
                else {
                    CustomToast(
                        applicationContext, "দুঃখিত! কারিগরি ত্রুটি হয়েছে ☹️ ${
                            resultData.message
                        }", "short", "neutral"
                    )
                }
            }

            is ApiState.Error -> {
                CustomToast(
                    applicationContext,
                    "দুঃখিত! আপনি নিবন্ধিত ব্যবহারকারী না ${(seatTypeLookUpState as ApiState.Error).errorMsg}",
                    "short",
                    "negative"
                )
                if ((seatTypeLookUpState as ApiState.Error).errorMsg.contains(
                        Constant.INVALID_REFRESH_TOKEN_RESPONSE_MSG
                    )
                ) {
                    logOutUser(
                        sharedPrefService,
                        encryptedSharedPrefService,
                        navController,
                        AppRoute.HOME
                    )
                }
            }
        }

        when (stationLookUpState) {
            is ApiState.Loading -> {}
            is ApiState.Success -> {
                val resultData = (stationLookUpState as ApiState.Success).data
                if (resultData.status == "Success") {
                    val newStations = resultData.data?.allLookUpList?.filter {
                        it.type ==
                                "station"
                    }
                        ?.mapNotNull { it.title_bn } ?: emptyList()
                    stations.clear()
                    stations.addAll(newStations)
                    CustomToast(
                        applicationContext, "stations fetched successfully", "short", "positive"
                    )
                }
                else {
                    CustomToast(
                        applicationContext, "দুঃখিত! কারিগরি ত্রুটি হয়েছে ☹️ ${
                            resultData.message
                        }", "short", "neutral"
                    )
                }
            }

            is ApiState.Error -> {
                CustomToast(
                    applicationContext,
                    "দুঃখিত! আপনি নিবন্ধিত ব্যবহারকারী না ${(stationLookUpState as ApiState.Error).errorMsg}",
                    "short",
                    "negative"
                )
                if ((stationLookUpState as ApiState.Error).errorMsg.contains(
                        Constant.INVALID_REFRESH_TOKEN_RESPONSE_MSG
                    )
                ) {
                    logOutUser(
                        sharedPrefService,
                        encryptedSharedPrefService,
                        navController,
                        AppRoute.HOME
                    )
                }
            }
        }

        when (journeyTimeLookUpState) {
            is ApiState.Loading -> {}
            is ApiState.Success -> {
                val resultData = (journeyTimeLookUpState as ApiState.Success).data
                if (resultData.status == "Success") {
                    val newJourneyTimes = resultData.data?.allLookUpList?.filter {
                        it.type ==
                                "journey_time"
                    }
                        ?.mapNotNull { it.title_bn } ?: emptyList()
                    journeyTimes.clear()
                    journeyTimes.addAll(newJourneyTimes)
                    CustomToast(
                        applicationContext, "journey time fetched successfully", "short", "positive"
                    )
                }
                else {
                    CustomToast(
                        applicationContext,
                        "দুঃখিত! কারিগরি ত্রুটি হয়েছে ☹️ ${resultData.message}",
                        "short",
                        "neutral"
                    )
                }
            }

            is ApiState.Error -> {
                CustomToast(
                    applicationContext,
                    "দুঃখিত! আপনি নিবন্ধিত ব্যবহারকারী না ${(journeyTimeLookUpState as ApiState.Error).errorMsg}",
                    "short",
                    "negative"
                )
                if ((journeyTimeLookUpState as ApiState.Error).errorMsg.contains(
                        Constant.INVALID_REFRESH_TOKEN_RESPONSE_MSG
                    )
                ) {
                    logOutUser(
                        sharedPrefService,
                        encryptedSharedPrefService,
                        navController,
                        AppRoute.HOME
                    )
                }
            }
        }
    }

    BottomSheetScaffold(scaffoldState = seatViewScaffoldState, sheetContent = {
        if (_isSeatViewPoppedUp) {

            _seats = listOf(
                TicketLookUpDto().apply {
                    seatNumber = "A1"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "A2"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "A3"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "A4"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "A5"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "A6"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "B1"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "B2"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "B3"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "B4"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "B5"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "B6"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "C1"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "C2"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "C3"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "C4"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "C5"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "C6"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "D1"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "D2"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "D3"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "D4"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "D5"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "D6"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "E1"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "E2"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "E3"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "E4"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "E5"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "E6"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "F1"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "F2"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "F3"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "F4"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "F5"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "F6"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "G1"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "G2"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "G3"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "G4"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "G5"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "G6"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "H1"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "H2"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "H3"; sold = true; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "H4"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "H5"; sold = false; isSelected = false
                },
                TicketLookUpDto().apply {
                    seatNumber = "H6"; sold = true; isSelected = false
                },
            )

            val seatBookingPopUpDTO = SeatBookingPopUpDto().apply {
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
            SeatBookingPopUp(applicationContext, seatBookingPopUpDTO)
        }
    }) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Constant.app_theme_color),
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
                        painter = painterResource(id = R.drawable.ic_global_map),
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
                        text = Constant.TICKET_SCREEN_HEADING,
                        style = TextStyle(
                            fontFamily = Constant.balooda2font,
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    )
                    Text(
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = Constant.TICKET_SCREEN_SUB_HEADING,
                        style = TextStyle(
                            fontFamily = Constant.balooda2font,
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

                            val dropDownMenuDTOForSeatType = DropDownMenuDto().apply {
                                heading = "সিটের ধরণ"
                                leadingIcon = R.drawable.ic_seat
                                items = seatCategories
//                                items = R.array.seat_category
                            }
                            DropDownMenu(homeViewModel, "seatType", dropDownMenuDTOForSeatType)

                            Spacer(modifier = Modifier.height(25.dp))

                            val dropDownMenuDTOForSource = DropDownMenuDto().apply {
                                heading = "যাত্রা শুরুর স্থান"
                                leadingIcon = R.drawable.ic_source
                                items = stations
                            }
                            DropDownMenu(homeViewModel, "source", dropDownMenuDTOForSource)

                            Spacer(modifier = Modifier.height(25.dp))

                            val dropDownMenuDTOForDestination = DropDownMenuDto().apply {
                                heading = "যাত্রা শেষের স্থান"
                                leadingIcon = R.drawable.ic_destination
                                items = stations
                            }
                            DropDownMenu(
                                homeViewModel, "destination", dropDownMenuDTOForDestination
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            /// DATE TIME PICKER
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    val pickerPopupDTO = PickerPopupDto().apply {
                                        heading = "যাত্রার তারিখ"
                                        leadingIcon = R.drawable.ic_calendar
                                    }
                                    DatePickerPopUp(homeViewModel, pickerPopupDTO)
                                }
                                Box(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    val dropDownMenuDTOForJourneyTime = DropDownMenuDto().apply {
                                        heading = "সময়"
                                        leadingIcon = R.drawable.ic_time
                                        items = journeyTimes
//                                items = R.array.seat_category
                                    }
                                    DropDownMenu(
                                        homeViewModel, "journeyTime", dropDownMenuDTOForJourneyTime
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(30.dp))

                            /// NUMERIC STEPPER BUTTONS FOR ADULT & CHILD
                            Row(
                                modifier = Modifier.fillMaxWidth(1.0f),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                val numericStepperDTOForAdult = NumericStepperDto().apply {
                                    heading = "যাত্রী সংখ্যা (প্রাপ্তবয়স্ক)"
                                    rowModifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .padding(end = 10.dp)
                                    itemCount = _passengerCount
                                    decreaseNumber = {
                                        if (_passengerCount > 0) homeViewModel.decrementPassengerCount()
                                        if (_passengerCount == 0) homeViewModel.initChildPassengerCount()
                                    }
                                    increaseNumber = {
                                        if (_passengerCount < 5) homeViewModel.incrementPassengerCount()
                                    }
                                }
                                NumericStepper(numericStepperDTOForAdult)

                                val numericStepperDTOForChild = NumericStepperDto().apply {
                                    heading = "যাত্রী সংখ্যা (শিশু)"
                                    rowModifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp)
                                    itemCount = _childPassengerCount
                                    decreaseNumber = {
                                        if (_childPassengerCount > 0) homeViewModel.decrementChildPassengerCount()
                                    }
                                    increaseNumber = {
                                        if (_childPassengerCount < 5 && _passengerCount > 0) homeViewModel.incrementChildPassengerCount()
                                        if (_passengerCount == 0) CustomToast(
                                            applicationContext,
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
                                    containerColor = Constant.app_theme_color,
                                    contentColor = Color.White,
                                    disabledContainerColor = Color.Gray,
                                    disabledContentColor = Color.White
                                ),
                                content = {
                                    Text(
                                        text = "টিকিট খুঁজুন",
                                        style = TextStyle(
                                            fontFamily = Constant.balooda2font,
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
                                modifier = Modifier.fillMaxWidth(),

                                onClick = {
                                    when (_selectedSeatType) {
                                        "" -> {
                                            homeViewModel.dismissSeatViewPopUp()
                                            CustomToast(
                                                applicationContext,
                                                "Please select a seat type",
                                                "short",
                                                "neutral"
                                            )
                                        }

                                        else -> {
                                            when (_selectedSource) {
                                                "" -> {
                                                    homeViewModel.dismissSeatViewPopUp()
                                                    CustomToast(
                                                        applicationContext,
                                                        "Please select a source location",
                                                        "short",
                                                        "neutral"
                                                    )
                                                }

                                                else -> {
                                                    when (_selectedDestination) {
                                                        "" -> {
                                                            homeViewModel.dismissSeatViewPopUp()
                                                            CustomToast(
                                                                applicationContext,
                                                                "Please select a destination" + " location",
                                                                "short",
                                                                "neutral"
                                                            )
                                                        }

                                                        else -> {
                                                            when (_selectedDate) {
                                                                "যাত্রার তারিখ" -> {
                                                                    homeViewModel.dismissSeatViewPopUp()
                                                                    CustomToast(
                                                                        applicationContext,
                                                                        "Please select a date",
                                                                        "short",
                                                                        "neutral"
                                                                    )
                                                                }

                                                                else -> {
                                                                    when (_selectedTime) {
                                                                        "যাত্রার সময়" -> {
                                                                            homeViewModel.dismissSeatViewPopUp()
                                                                            CustomToast(
                                                                                applicationContext,
                                                                                "Please " + "select a time",
                                                                                "short",
                                                                                "neutral"
                                                                            )
                                                                        }

                                                                        else -> {
                                                                            when (_passengerCount) {
                                                                                0 -> {
                                                                                    homeViewModel.dismissSeatViewPopUp()
                                                                                    CustomToast(
                                                                                        applicationContext,
                                                                                        "Please add a passenger",
                                                                                        "short",
                                                                                        "neutral"
                                                                                    )
                                                                                }

                                                                                else -> {
                                                                                    homeViewModel.searchTicket(
                                                                                        ticketLookUpDTO,
                                                                                        sharedPrefService,
                                                                                        encryptedSharedPrefService
                                                                                    )
                                                                                    homeViewModel.popUpSeatView()
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
    val navController = rememberNavController()
    TicketScreen(navController)
}
