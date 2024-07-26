/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.api

import com.akash.nou.model.AuthResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthAPI {
    @POST("/login/")
    fun setPhoneNo(
        @Header("phone_no") phone_no: String,
    ): Call<AuthResponse>

    @POST("/otp-verify/")
    fun verifyOTP(
        @Header("phone_no") phone_no: String,
        @Header("otp") otp: String,
    ): Call<AuthResponse>
}