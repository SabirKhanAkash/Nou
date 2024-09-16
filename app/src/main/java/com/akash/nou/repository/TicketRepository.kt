/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.repository

import com.akash.nou.api.TicketAPI
import com.akash.nou.dto.TicketLookUpDTO
import com.akash.nou.model.SeatBookResponse
import com.akash.nou.model.SoldTicketListResponse
import com.akash.nou.model.Tickets
import retrofit2.Call

class TicketRepository(private val service: TicketAPI) {
    fun searchTicket(refreshToken: String?, accessToken: String?, ticketLookUpDto: TicketLookUpDTO):
            Call<Tickets> {
        return service.searchTicket(
            refreshToken = refreshToken,
            accessToken = accessToken,
            pageNo = ticketLookUpDto.pageNo,
            date = ticketLookUpDto.date,
            time = ticketLookUpDto.time,
            seatCategory = ticketLookUpDto.seatCategory,
            source = ticketLookUpDto.source,
            destination = ticketLookUpDto.destination,
            passengerCount = ticketLookUpDto.passengerCount,
            childPassengerCount = ticketLookUpDto.childPassengerCount,
        )
    }

    fun bookSeat(
        phoneNo: String,
        authToken: String,
        refreshToken: String,
        ticketBody: TicketLookUpDTO
    ): Call<SeatBookResponse> {
        return service.bookSeat(phoneNo, authToken, refreshToken, ticketBody)
    }

    fun getSoldTicketList(
        phoneNo: String,
        authToken: String,
        refreshToken: String
    ): Call<SoldTicketListResponse> {
        return service.getSoldTicketList(phoneNo, authToken, refreshToken)
    }
}