/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.api

import com.akash.nou.model.AuthResponse
import com.akash.nou.model.SeatBookResponse
import com.akash.nou.model.SeatMap
import com.akash.nou.model.SoldTicketListResponse
import com.akash.nou.model.TicketBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TicketAPI {
    @POST("/ticket/search/")
    fun searchTicket(
        @Header("phone_no") phone_no: String,
        @Header("authToken") authToken: String,
        @Header("refreshToken") refreshToken: String,
        @Body ticketBody: TicketBody
    ): Call<SeatMap>

    @POST("/ticket/book-seat/")
    fun bookSeat(
        @Header("phone_no") phone_no: String,
        @Header("authToken") authToken: String,
        @Header("refreshToken") refreshToken: String,
        @Body ticketBody: TicketBody
    ): Call<SeatBookResponse>

    @POST("/ticket/sold-list/")
    fun getSoldTicketList(
        @Header("phone_no") phone_no: String,
        @Header("authToken") authToken: String,
        @Header("refreshToken") refreshToken: String,
    ): Call<SoldTicketListResponse>
}