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
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface TicketAPI {
    @POST("/ticket/search/")
    fun searchTicket(
        @Header("phoneNo") phoneNo: String,
        @Header("auth") authToken: String,
        @Header("refresh") refreshToken: String,
        @Body ticketBody: TicketLookUpDTO
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