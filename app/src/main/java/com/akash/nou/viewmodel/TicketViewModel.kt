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
import com.akash.nou.dto.AllLookUpDto
import com.akash.nou.dto.TicketLookUpDTO
import com.akash.nou.model.SeatBookResponse
import com.akash.nou.model.SoldTicketListResponse
import com.akash.nou.model.Tickets
import com.akash.nou.repository.TicketRepository
import com.akash.nou.utils.EncryptedSharedPreference
import com.akash.nou.utils.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TicketViewModel(private val repository: TicketRepository) : ViewModel() {
    val ticketsLiveData: MutableLiveData<GenericApiResponse<Tickets>> = MutableLiveData()
    val seatCategoryLiveData: MutableLiveData<GenericApiResponse<Tickets>> = MutableLiveData()
    val stationLiveData: MutableLiveData<GenericApiResponse<Tickets>> = MutableLiveData()
    val journeyTimeLiveData: MutableLiveData<GenericApiResponse<Tickets>> = MutableLiveData()
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
    private val encryptedSharedPreference: EncryptedSharedPreference by lazy { EncryptedSharedPreference() }

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
        ticketLookUpDto: TicketLookUpDTO
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            try {
                val response =
                    repository.searchTicket(
                        null,
                        "Bearer ${sharedPref.getString(context, "accessToken").toString()}",
                        ticketLookUpDto
                    )
                        .execute()
                if (response.isSuccessful) {
                    isLoading.postValue(false)
                    ticketsLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                }
                else if (response.code() == 401) {
                    val fetchNewTokenResponse =
                        repository.searchTicket(
                            encryptedSharedPreference.getString(
                                context,
                                "refreshToken"
                            ).toString(),
                            null,
                            ticketLookUpDto
                        ).execute()

                    if (fetchNewTokenResponse.isSuccessful) {
                        sharedPref.setString(
                            context, "accessToken",
                            fetchNewTokenResponse.body()?.accessToken.toString()
                        )
                        encryptedSharedPreference.setString(
                            context, "refreshToken",
                            fetchNewTokenResponse.body()?.refreshToken.toString()
                        )

                        val responseWithUpdatedTokens =
                            repository.searchTicket(
                                null,
                                "Bearer ${sharedPref.getString(context, "accessToken")}",
                                ticketLookUpDto
                            ).execute()

                        if (responseWithUpdatedTokens.isSuccessful) {
                            isLoading.postValue(false)
                            ticketsLiveData.postValue(
                                GenericApiResponse.Success(
                                    responseWithUpdatedTokens.body()!!
                                )
                            )
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

    fun getAllLookUpInfos(context: Context, allLookUpDto: AllLookUpDto) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.allLookUp(
                        null,
                        "Bearer ${sharedPref.getString(context, "accessToken").toString()}",
                        allLookUpDto
                    )
                        .execute()
                if (response.isSuccessful) {
                    if(allLookUpDto.type == "seat_category")
                        seatCategoryLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                    if(allLookUpDto.type == "station")
                        stationLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                    if(allLookUpDto.type == "journey_time")
                        journeyTimeLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                }
                else if (response.code() == 401) {
                    val fetchNewTokenResponse =
                        repository.allLookUp(
                            encryptedSharedPreference.getString(
                                context,
                                "refreshToken"
                            ).toString(),
                            null,
                            allLookUpDto
                        ).execute()

                    if (fetchNewTokenResponse.isSuccessful) {
                        sharedPref.setString(
                            context, "accessToken",
                            fetchNewTokenResponse.body()?.accessToken.toString()
                        )
                        encryptedSharedPreference.setString(
                            context, "refreshToken",
                            fetchNewTokenResponse.body()?.refreshToken.toString()
                        )

                        val responseWithUpdatedTokens =
                            repository.allLookUp(
                                null,
                                "Bearer ${sharedPref.getString(context, "accessToken")}",
                                allLookUpDto
                            ).execute()

                        if (responseWithUpdatedTokens.isSuccessful) {
                            if(allLookUpDto.type == "seat_category")
                                seatCategoryLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                            if(allLookUpDto.type == "station")
                                stationLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                            if(allLookUpDto.type == "journey_time")
                                journeyTimeLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                        }
                        else {
                            if(allLookUpDto.type == "seat_category")
                                seatCategoryLiveData.postValue(GenericApiResponse.Forbidden("invalid refresh token"))
                            if(allLookUpDto.type == "station")
                                stationLiveData.postValue(GenericApiResponse.Forbidden("invalid refresh token"))
                            if(allLookUpDto.type == "journey_time")
                                journeyTimeLiveData.postValue(GenericApiResponse.Forbidden("invalid refresh token"))
                        }
                    }
                    else {
                        if(allLookUpDto.type == "seat_category")
                            seatCategoryLiveData.postValue(GenericApiResponse.Error(
                                "Oops! Something went wrong. :(\n${
                                    response.errorBody().toString()
                                }"
                            ))
                        if(allLookUpDto.type == "station")
                            stationLiveData.postValue(GenericApiResponse.Error(
                                "Oops! Something went wrong. :(\n${
                                    response.errorBody().toString()
                                }"
                            ))
                        if(allLookUpDto.type == "journey_time")
                            journeyTimeLiveData.postValue(GenericApiResponse.Error(
                                "Oops! Something went wrong. :(\n${
                                    response.errorBody().toString()
                                }"
                            ))
                    }
                }
                else {
                    if(allLookUpDto.type == "seat_category")
                        seatCategoryLiveData.postValue(GenericApiResponse.Error(
                            "Oops! Something went wrong. :(\n${
                                response.errorBody().toString()
                            }"
                        ))
                    if(allLookUpDto.type == "station")
                        stationLiveData.postValue(GenericApiResponse.Error(
                            "Oops! Something went wrong. :(\n${
                                response.errorBody().toString()
                            }"
                        ))
                    if(allLookUpDto.type == "journey_time")
                        journeyTimeLiveData.postValue(GenericApiResponse.Error(
                            "Oops! Something went wrong. :(\n${
                                response.errorBody().toString()
                            }"
                        ))
                }
            } catch (e: Exception) {
                Log.d("tag", "${e.stackTraceToString()}")
                if(allLookUpDto.type == "seat_category")
                    seatCategoryLiveData.postValue(GenericApiResponse.Error(e.message))
                if(allLookUpDto.type == "station")
                    stationLiveData.postValue(GenericApiResponse.Error(e.message))
                if(allLookUpDto.type == "journey_time")
                    journeyTimeLiveData.postValue(GenericApiResponse.Error(e.message))
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
