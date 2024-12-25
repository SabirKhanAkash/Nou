package com.akash.nou.service

import ApiRoute
import com.akash.nou.model.AuthRequest
import com.akash.nou.model.AuthResponse
import com.akash.nou.model.Ticket
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST(ApiRoute.REFRESH_ACCESS_TOKEN)
    fun refreshAccessToken(
        @Header("refresh") refreshToken: String?,
    ): Call<AuthResponse>

    @POST(ApiRoute.LOGIN)
    suspend fun login(@Body authReq: AuthRequest): AuthResponse

    @POST(ApiRoute.VERIFY_OTP)
    suspend fun verifyOtp(@Body authReq: AuthRequest): AuthResponse

    @GET(ApiRoute.LOOKUP_ALL)
    suspend fun allLookUp(
        @Query("pageNo") pageNo: Int,
        @Query("perPage") perPage: Int,
        @Query("type") type: String,
        @Query("vendorId") vendorId: String,
        @Query("orderValue") orderValue: Int,
    ): Ticket

    @GET(ApiRoute.SEARCH_TICKET)
    fun searchTicket(
        @Query("pageNo") pageNo: Int,
        @Query("date") date: String,
        @Query("time") time: String,
        @Query("seatCategory") seatCategory: String,
        @Query("source") source: String,
        @Query("destination") destination: String,
        @Query("passengerCount") passengerCount: Int,
        @Query("childPassengerCount") childPassengerCount: Int,
    ): Ticket
}