/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.repository

import com.akash.nou.api.AuthAPI
import com.akash.nou.api.TicketAPI
import com.akash.nou.model.AuthResponse
import com.akash.nou.model.SeatBookResponse
import com.akash.nou.model.SeatMap
import com.akash.nou.model.SoldTicketListResponse
import com.akash.nou.model.TicketBody
import retrofit2.Call

class TicketRepository(private val service: TicketAPI) {
    fun searchTicket(phone_no: String, authToken: String, refreshToken: String, ticketBody: TicketBody): Call<SeatMap> {
        return service.searchTicket(phone_no, authToken, refreshToken, ticketBody)
    }

    fun bookSeat(phone_no: String, authToken: String, refreshToken: String, ticketBody: TicketBody): Call<SeatBookResponse> {
        return service.bookSeat(phone_no, authToken, refreshToken, ticketBody)
    }

    fun getSoldTicketList(phone_no: String, authToken: String, refreshToken: String): Call<SoldTicketListResponse> {
        return service.getSoldTicketList(phone_no, authToken, refreshToken)
    }
}