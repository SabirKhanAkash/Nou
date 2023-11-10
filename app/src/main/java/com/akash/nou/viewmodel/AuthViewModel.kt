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
import com.akash.nou.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    val authLiveData: MutableLiveData<GenericApiResponse<AuthResponse>> = MutableLiveData()
    val otpLiveData: MutableLiveData<GenericApiResponse<AuthResponse>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun verifyPhoneNumber(phone_no: String) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.setPhoneNo(phone_no).execute()
                if (response.isSuccessful) {
                    val authResponse: AuthResponse = response.body()!!
                    isLoading.postValue(false)
                    authLiveData.postValue(GenericApiResponse.Success(authResponse))
                } else {
                    isLoading.postValue(false)
                    authLiveData.postValue(
                        GenericApiResponse.Error(
                            "Oops! Something went wrong. :(\n${
                                response.errorBody().toString()
                            }"
                        )
                    )
                }
            } catch (e: Exception) {
                isLoading.postValue(false)
                authLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }

    fun verifyOTP(phone_no: String, otp: String) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.verifyOTP(phone_no, otp).execute()
                if (response.isSuccessful) {
                    val authResponse: AuthResponse = response.body()!!
                    isLoading.postValue(false)
                    otpLiveData.postValue(GenericApiResponse.Success(authResponse))
                } else {
                    isLoading.postValue(false)
                    otpLiveData.postValue(
                        GenericApiResponse.Error(
                            "Oops! Something went wrong. :(\n${
                                response.errorBody().toString()
                            }"
                        )
                    )
                }
            } catch (e: Exception) {
                isLoading.postValue(false)
                otpLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }
}
