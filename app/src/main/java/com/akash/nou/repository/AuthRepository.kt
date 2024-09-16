/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.repository

import com.akash.nou.api.AuthAPI
import com.akash.nou.dto.AuthDto
import com.akash.nou.model.AuthResponse
import retrofit2.Call

class AuthRepository(private val service: AuthAPI) {
    fun login(authDto: AuthDto): Call<AuthResponse> {
        return service.login(authDto)
    }

    fun verifyOTP(authDto: AuthDto): Call<AuthResponse> {
        return service.verifyOTP(authDto)
    }
}