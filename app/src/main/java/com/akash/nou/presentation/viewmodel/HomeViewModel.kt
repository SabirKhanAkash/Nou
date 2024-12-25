/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.presentation.viewmodel

import Constant
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.nou.dto.AllLookUpDto
import com.akash.nou.dto.TicketLookUpDto
import com.akash.nou.model.Ticket
import com.akash.nou.repository.Repository
import com.akash.nou.service.EncryptedSharedPrefService
import com.akash.nou.service.SharedPrefService
import com.akash.nou.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _ticketLookUpState: MutableStateFlow<ApiState<Ticket>> =
        MutableStateFlow(ApiState.Loading)
    val ticketLookUpState = _ticketLookUpState.asStateFlow()
    private val _seatTypeLookUpState: MutableStateFlow<ApiState<Ticket>> =
        MutableStateFlow(ApiState.Loading)
    val seatTypeLookUpState = _seatTypeLookUpState.asStateFlow()
    private val _stationLookUpState: MutableStateFlow<ApiState<Ticket>> =
        MutableStateFlow(ApiState.Loading)
    val stationLookUpState = _stationLookUpState.asStateFlow()
    private val _journeyTimeLookUpState: MutableStateFlow<ApiState<Ticket>> =
        MutableStateFlow(ApiState.Loading)
    val journeyTimeLookUpState = _journeyTimeLookUpState.asStateFlow()

    private val _passengerCount = MutableLiveData<Int>().apply { value = 0 }
    private val _childPassengerCount = MutableLiveData<Int>().apply { value = 0 }
    private val _selectedSeatType = MutableLiveData<String>()
    private val _selectedSource = MutableLiveData<String>()
    private val _selectedDestination = MutableLiveData<String>()
    private val _selectedDate = MutableLiveData<String>()
    private val _selectedTime = MutableLiveData<String>()
    private val _isSeatViewPoppedUp = MutableLiveData<Boolean>()
    private val _seats = MutableLiveData<List<TicketLookUpDto>>()
    private val _numberOfColumns = MutableLiveData<Int>().apply { value = 6 }

    val passengerCount: LiveData<Int> = _passengerCount
    val childPassengerCount: LiveData<Int> = _childPassengerCount
    val selectedSeatType: LiveData<String> = _selectedSeatType
    val selectedSource: LiveData<String> = _selectedSource
    val selectedDestination: LiveData<String> = _selectedDestination
    val selectedDate: LiveData<String> = _selectedDate
    val selectedTime: LiveData<String> = _selectedTime
    val isSeatViewPoppedUp: LiveData<Boolean> = _isSeatViewPoppedUp
    val seats: LiveData<List<TicketLookUpDto>> = _seats
    val numberOfColumns: LiveData<Int> = _numberOfColumns


    fun setDropDownItem(item: String, dropDownType: String) {
        when (dropDownType) {
            "seatType" -> {
                _selectedSeatType.value = item
            }

            "source" -> {
                _selectedSource.value = item
            }

            "destination" -> {
                _selectedDestination.value = item
            }

            "journeyTime" -> {
                _selectedTime.value = item
            }
        }

    }

    fun setDate(date: String) {
        _selectedDate.value = date
    }

    fun setTime(time: String) {
        _selectedTime.value = time
    }

    fun dismissSeatViewPopUp() {
        _isSeatViewPoppedUp.value = false
    }

    fun popUpSeatView() {
        _isSeatViewPoppedUp.value = true
    }

    fun decrementPassengerCount() {
        _passengerCount.value = _passengerCount.value?.minus(1)
    }

    fun incrementPassengerCount() {
        _passengerCount.value = _passengerCount.value?.plus(1)
    }

    fun decrementChildPassengerCount() {
        _childPassengerCount.value = _childPassengerCount.value?.minus(1)
    }

    fun incrementChildPassengerCount() {
        _childPassengerCount.value = _childPassengerCount.value?.plus(1)
    }

    fun initChildPassengerCount() {
        _childPassengerCount.value = 0
    }

    fun searchTicket(
        ticketLookUpDto: TicketLookUpDto,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService
    ) {
        viewModelScope.launch {
            try {
                _ticketLookUpState.value = ApiState.Loading

                val result = withContext(Dispatchers.IO) {
                    repository.searchTicket(
                        ticketLookUpDto,
                        sharedPrefService,
                        encryptedSharedPrefService
                    )
                }

                _ticketLookUpState.value = result
            } catch (e: Exception) {
                _ticketLookUpState.value =
                    ApiState.Error(e.message ?: Constant.UNEXPECTED_ERROR_MSG)
            }
        }
    }

    fun getAllLookUpInfos(
        allLookUpDto: AllLookUpDto,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService
    ) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    repository.allLookUp(
                        allLookUpDto, sharedPrefService, encryptedSharedPrefService
                    )
                }

                when (allLookUpDto.type) {
                    "seat_category" -> {
                        _seatTypeLookUpState.value = result
                    }

                    "station" -> {
                        _stationLookUpState.value = result
                    }

                    "journey_time" -> {
                        _journeyTimeLookUpState.value = result
                    }
                }
            } catch (e: Exception) {
                when (allLookUpDto.type) {
                    "seat_category" -> {
                        _seatTypeLookUpState.value =
                            ApiState.Error(e.message ?: Constant.UNEXPECTED_ERROR_MSG)
                    }

                    "station" -> {
                        _stationLookUpState.value =
                            ApiState.Error(e.message ?: Constant.UNEXPECTED_ERROR_MSG)
                    }

                    "journey_time" -> {
                        _journeyTimeLookUpState.value =
                            ApiState.Error(e.message ?: Constant.UNEXPECTED_ERROR_MSG)
                    }
                }
            }
        }
    }

}
