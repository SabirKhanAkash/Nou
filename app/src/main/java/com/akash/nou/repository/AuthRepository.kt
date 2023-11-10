/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.repository

import com.akash.nou.api.AuthAPI
import com.akash.nou.model.AuthResponse
import retrofit2.Call

class AuthRepository(private val service: AuthAPI) {
    fun setPhoneNo(phone_no: String): Call<AuthResponse> {
        return service.setPhoneNo(phone_no)
    }

    fun verifyOTP(phone_no: String, otp: String): Call<AuthResponse> {
        return service.verifyOTP(phone_no, otp)
    }
}