/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
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