package com.akash.nou.presentation.viewmodel

import Constant
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.nou.model.AuthRequest
import com.akash.nou.model.AuthResponse
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
class AuthViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
//    private val _uiEvent = Channel<UiEvent>()
//    val uiEvent = _uiEvent.receiveAsFlow()
//
//    fun onEvent(event: AuthEvent) {
//        when (event) {
//            is AuthEvent.OnLogin -> {
//                viewModelScope.launch { repository.login(event.phoneNo) }
//                sendUIEvent(UiEvent.Navigate(Routes.OTP))
//            }
//
//            is AuthEvent.OnOtpVerify -> {
//
//            }
//        }
//    }
//
//    private fun sendUIEvent(event: UiEvent) {
//        viewModelScope.launch { _uiEvent.send(event) }
//    }

    private val _loginState: MutableStateFlow<ApiState<AuthResponse>> =
        MutableStateFlow(ApiState.Loading)
    val loginState = _loginState.asStateFlow()
    private val _otpVerifyState: MutableStateFlow<ApiState<AuthResponse>> =
        MutableStateFlow(ApiState.Loading)
    val otpVerifyState = _otpVerifyState.asStateFlow()
    private val _phoneNumber = MutableLiveData<String>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _isPhoneNumberValid = MutableLiveData<Boolean>()
    val phoneNumber: LiveData<String> = _phoneNumber
    val isLoading: LiveData<Boolean> = _isLoading
    val isPhoneNumberValid: LiveData<Boolean> = _isPhoneNumberValid
    private val _otp = MutableLiveData<String>()
    val otp: LiveData<String> = _otp
    private val _isOTPVerified = MutableLiveData<Boolean>()
    val isOTPVerified: LiveData<Boolean> = _isOTPVerified

    fun setOtp(otp: String) {
        _otp.value = otp
    }

    fun setOtpVerified(value: Boolean) {
        _isOTPVerified.value = value
    }

    fun startLoading() {
        _isLoading.value = true
    }

    fun dismissLoading() {
        _isLoading.value = false
    }

    fun setPhoneNumber(phoneNo: String) {
        _phoneNumber.value = phoneNo
        _isPhoneNumberValid.value = phoneNo.length == 11
    }

    fun login(
        authReq: AuthRequest,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService
    ) {
        viewModelScope.launch {
            try {
                _loginState.value = ApiState.Loading

                val result = withContext(Dispatchers.IO) {
                    repository.login(authReq, sharedPrefService, encryptedSharedPrefService)
                }

                _loginState.value = result
            } catch (e: Exception) {
                _loginState.value = ApiState.Error(e.message ?: Constant.UNEXPECTED_ERROR_MSG)
            }
        }
    }

    fun verifyOTP(
        authReq: AuthRequest,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService
    ) {
        viewModelScope.launch {
            try {
                _otpVerifyState.value = ApiState.Loading

                val result = withContext(Dispatchers.IO) {
                    repository.verifyOtp(authReq, sharedPrefService, encryptedSharedPrefService)
                }

                _otpVerifyState.value = result
            } catch (e: Exception) {
                _otpVerifyState.value = ApiState.Error(e.message ?: Constant.UNEXPECTED_ERROR_MSG)
            }
        }
    }
}