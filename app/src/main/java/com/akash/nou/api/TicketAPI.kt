/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.api

import com.akash.nou.dto.TicketLookUpDTO
import com.akash.nou.model.SeatBookResponse
import com.akash.nou.model.SoldTicketListResponse
import com.akash.nou.model.Tickets
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TicketAPI {
    @GET("/ticket/search")
    fun searchTicket(
        @Header("refresh") refreshToken: String?,
        @Header("Authorization") accessToken: String?,
        @Query("pageNo") pageNo: Int,
        @Query("date") date: String,
        @Query("time") time: String,
        @Query("seatCategory") seatCategory: String,
        @Query("source") source: String,
        @Query("destination") destination: String,
        @Query("passengerCount") passengerCount: Int,
        @Query("childPassengerCount") childPassengerCount: Int,
    ): Call<Tickets>

    @GET("/all-lookup/index")
    fun allLookUp(
        @Header("refresh") refreshToken: String?,
        @Header("Authorization") accessToken: String?,
        @Query("pageNo") pageNo: Int,
        @Query("perPage") perPage: Int,
        @Query("type") type: String,
        @Query("vendorId") vendorId: String,
        @Query("orderValue") orderValue: Int,
    ): Call<Tickets>

    @PUT("/ticket/book-seat/")
    fun bookSeat(
        @Header("phoneNo") phoneNo: String,
        @Header("auth") authToken: String,
        @Header("refresh") refreshToken: String,
        @Body ticketBody: TicketLookUpDTO
    ): Call<SeatBookResponse>

    @POST("/ticket/sold-list/")
    fun getSoldTicketList(
        @Header("phoneNo") phoneNo: String,
        @Header("auth") authToken: String,
        @Header("refresh") refreshToken: String,
    ): Call<SoldTicketListResponse>
}