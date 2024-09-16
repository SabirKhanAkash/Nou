/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.viewmodel

import GenericApiResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.nou.dto.AuthDto
import com.akash.nou.model.AuthResponse
import com.akash.nou.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(private val authrepository: AuthRepository) : ViewModel() {
    val authLiveData: MutableLiveData<GenericApiResponse<AuthResponse>> = MutableLiveData()
    val otpLiveData: MutableLiveData<GenericApiResponse<AuthResponse>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val _phoneNumber = MutableLiveData<String>()
    private val _isPhoneNumberValid = MutableLiveData<Boolean>()
    private val _isOTPVerified = MutableLiveData<Boolean>()
    private val _otp = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber
    val otp: LiveData<String> = _otp
    val isPhoneNumberValid: LiveData<Boolean> = _isPhoneNumberValid
    val isOTPVerified: LiveData<Boolean> = _isOTPVerified

    fun setPhoneNumber(phoneNo: String) {
        _phoneNumber.value = phoneNo
        _isPhoneNumberValid.value = phoneNo.length == 11
    }

    fun setOtp(otp: String) {
        _otp.value = otp
    }

    fun setOtpVerified(value: Boolean) {
        _isOTPVerified.value = value
    }

    fun login(authDto: AuthDto) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authrepository.login(authDto).execute()

                if (response.isSuccessful) {
                    isLoading.postValue(false)
                    authLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                }
                else if (response.code() == 401) {
                    isLoading.postValue(false)
                    authLiveData.postValue(GenericApiResponse.Forbidden("দুঃখিত! আপনি নিবন্ধিত ব্যবহারকারী না"))
                }
                else {
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
                Log.d("tag", "${e.stackTraceToString()}")
                isLoading.postValue(false)
                authLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }

    fun verifyOTP(authDto: AuthDto) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authrepository.verifyOTP(authDto).execute()
                if (response.isSuccessful) {
                    isLoading.postValue(false)
                    otpLiveData.postValue(GenericApiResponse.Success(response.body()!!))
                }
                else if (response.code() == 403) {
                    isLoading.postValue(false)
                    authLiveData.postValue(GenericApiResponse.Forbidden("ওটিপি কোডটি ভুল হয়েছে"))
                }
                else {
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
                Log.d("tag", "${e.stackTraceToString()}")
                isLoading.postValue(false)
                otpLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }
}
