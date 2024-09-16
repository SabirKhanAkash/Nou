/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.api

import com.akash.nou.dto.AuthDto
import com.akash.nou.model.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("/login/")
    fun login(
        @Body authDto: AuthDto,
    ): Call<AuthResponse>

    @POST("/verify-otp/")
    fun verifyOTP(
        @Body authDto: AuthDto,
    ): Call<AuthResponse>
}