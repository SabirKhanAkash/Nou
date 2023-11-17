/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.viewmodel

import GenericApiResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.nou.model.AuthResponse
import com.akash.nou.model.SeatBookResponse
import com.akash.nou.model.SeatMap
import com.akash.nou.model.SoldTicketListResponse
import com.akash.nou.model.TicketBody
import com.akash.nou.repository.AuthRepository
import com.akash.nou.repository.TicketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TicketViewModel(private val repository: TicketRepository) : ViewModel() {

    val seatMapLiveData: MutableLiveData<GenericApiResponse<SeatMap>> = MutableLiveData()
    val bookTicketLiveData: MutableLiveData<GenericApiResponse<SeatBookResponse>> = MutableLiveData()
    val soldTicketListLiveData: MutableLiveData<GenericApiResponse<SoldTicketListResponse>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun searchTicket(phone_no: String, authToken: String, refreshToken: String, ticketBody: TicketBody) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            try {
                val response = repository.searchTicket(phone_no, authToken, refreshToken, ticketBody).execute()
                if (response.isSuccessful) {
                    isLoading.postValue(false)
                    seatMapLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                } else {
                    isLoading.postValue(false)
                    seatMapLiveData.postValue(
                        GenericApiResponse.Error(
                            "Oops! Something went wrong. :(\n${
                                response.errorBody().toString()
                            }"
                        )
                    )
                }
            } catch (e: Exception) {
                isLoading.postValue(false)
                seatMapLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }

    fun bookSeat(phone_no: String, authToken: String, refreshToken: String, ticketBody: TicketBody) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            try {
                val response = repository.bookSeat(phone_no, authToken, refreshToken, ticketBody).execute()
                if (response.isSuccessful) {
                    isLoading.postValue(false)
                    bookTicketLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                } else {
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

    fun getSoldTicketList(phone_no: String, authToken: String, refreshToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            try {
                val response = repository.getSoldTicketList(phone_no, authToken, refreshToken).execute()
                if (response.isSuccessful) {
                    isLoading.postValue(false)
                    soldTicketListLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                } else {
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
