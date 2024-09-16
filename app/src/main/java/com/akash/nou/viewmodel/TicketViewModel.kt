/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.viewmodel

import GenericApiResponse
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.nou.dto.TicketLookUpDTO
import com.akash.nou.model.SeatBookResponse
import com.akash.nou.model.SoldTicketListResponse
import com.akash.nou.model.Tickets
import com.akash.nou.repository.TicketRepository
import com.akash.nou.utils.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TicketViewModel(private val repository: TicketRepository) : ViewModel() {
    val ticketsLiveData: MutableLiveData<GenericApiResponse<Tickets>> = MutableLiveData()
    val bookTicketLiveData: MutableLiveData<GenericApiResponse<SeatBookResponse>> =
        MutableLiveData()
    val soldTicketListLiveData: MutableLiveData<GenericApiResponse<SoldTicketListResponse>> =
        MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val _passengerCount = MutableLiveData<Int>().apply { value = 0 }
    private val _childPassengerCount = MutableLiveData<Int>().apply { value = 0 }
    private val _selectedSeatType = MutableLiveData<String>()
    private val _selectedSource = MutableLiveData<String>()
    private val _selectedDestination = MutableLiveData<String>()
    private val _selectedDate = MutableLiveData<String>()
    private val _selectedTime = MutableLiveData<String>()
    private val _isSeatViewPoppedUp = MutableLiveData<Boolean>()
    private val _seats = MutableLiveData<List<TicketLookUpDTO>>()
    private val _numberOfColumns = MutableLiveData<Int>().apply { value = 6 }
    private val sharedPref: SharedPref by lazy { SharedPref() }

    val passengerCount: LiveData<Int> = _passengerCount
    val childPassengerCount: LiveData<Int> = _childPassengerCount
    val selectedSeatType: LiveData<String> = _selectedSeatType
    val selectedSource: LiveData<String> = _selectedSource
    val selectedDestination: LiveData<String> = _selectedDestination
    val selectedDate: LiveData<String> = _selectedDate
    val selectedTime: LiveData<String> = _selectedTime
    val isSeatViewPoppedUp: LiveData<Boolean> = _isSeatViewPoppedUp
    val seats: LiveData<List<TicketLookUpDTO>> = _seats
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
        context: Context,
        refreshToken: String,
        authToken: String,
        ticketLookUpDto: TicketLookUpDTO
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            try {
                val response =
                    repository.searchTicket(null, "Bearer $authToken", ticketLookUpDto)
                        .execute()
                Log.d("tag", "1. isSuccessful: ${response.isSuccessful}")
                Log.d("tag", "1. body: ${response.body().toString()}")
                if (response.isSuccessful) {
                    isLoading.postValue(false)
                    ticketsLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                }
                else if (response.code() == 401) {
                    val response =
                        repository.searchTicket(refreshToken, null, ticketLookUpDto)
                            .execute()
                    Log.d("tag", "2. isSuccessful: ${response.isSuccessful}")
                    Log.d("tag", "2. body: ${response.body().toString()}")
                    if (response.isSuccessful) {
                        sharedPref.setString(
                            context, "accessToken",
                            response.body()?.token.toString()
                        )
                        val accessToken = response.body()?.token.toString()
                        Log.d("tag", "3. new accessToken: ${accessToken}")
                        val response =
                            repository.searchTicket(null, "Bearer $accessToken", ticketLookUpDto)
                                .execute()
                        Log.d("tag", "3. isSuccessful: ${response.isSuccessful}")
                        Log.d("tag", "3. body: ${response.body().toString()}")

                        if (response.isSuccessful) {
                            isLoading.postValue(false)
                            ticketsLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                        }
                        else {
                            isLoading.postValue(false)
                            ticketsLiveData.postValue(GenericApiResponse.Forbidden("invalid refresh token"))
                        }
                    }
                    else {
                        isLoading.postValue(false)
                        ticketsLiveData.postValue(
                            GenericApiResponse.Error(
                                "Oops! Something went wrong. :(\n${
                                    response.errorBody().toString()
                                }"
                            )
                        )
                    }
                }
                else {
                    isLoading.postValue(false)
                    ticketsLiveData.postValue(
                        GenericApiResponse.Error(
                            "Oops! Something went wrong. :(\n${
                                response.errorBody().toString()
                            }"
                        )
                    )
                }
            } catch (e: Exception) {
                Log.d("tag", "${e.stackTraceToString()}")
                isLoading.postValue(false)
                ticketsLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }

    fun bookSeat(
        phoneNo: String,
        authToken: String,
        refreshToken: String,
        ticketBody: TicketLookUpDTO
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            try {
                val response =
                    repository.bookSeat(phoneNo, authToken, refreshToken, ticketBody).execute()
                if (response.isSuccessful) {
                    isLoading.postValue(false)
                    bookTicketLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                }
                else {
                    isLoading.postValue(false)
                    bookTicketLiveData.postValue(
                        GenericApiResponse.Error(
                            "Oops! Something went wrong. :(\n${
                                response.errorBody().toString()
                            }"
                        )
                    )
                }
            } catch (e: Exception) {
                isLoading.postValue(false)
                bookTicketLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }

    fun getSoldTicketList(phoneNo: String, authToken: String, refreshToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            try {
                val response =
                    repository.getSoldTicketList(phoneNo, authToken, refreshToken).execute()
                if (response.isSuccessful) {
                    isLoading.postValue(false)
                    soldTicketListLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                }
                else {
                    isLoading.postValue(false)
                    soldTicketListLiveData.postValue(
                        GenericApiResponse.Error(
                            "Oops! Something went wrong. :(\n${
                                response.errorBody().toString()
                            }"
                        )
                    )
                }
            } catch (e: Exception) {
                isLoading.postValue(false)
                soldTicketListLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }
}
