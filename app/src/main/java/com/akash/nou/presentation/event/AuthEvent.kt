package com.akash.nou.presentation.event

import com.akash.nou.model.AuthRequest

sealed class AuthEvent {
    data class OnLogin(val phoneNo: String) : AuthEvent()
    data class OnOtpVerify(val authRequest: AuthRequest) : AuthEvent()
}