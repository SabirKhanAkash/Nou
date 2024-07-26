/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.model

import com.akash.nou.dto.TicketLookUpDTO

data class SoldTicketListResponse(
    var ticketBody: TicketLookUpDTO,
    var count: Int,
    var status: String,
    var message: String
)