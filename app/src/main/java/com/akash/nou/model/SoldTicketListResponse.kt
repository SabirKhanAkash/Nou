package com.akash.nou.model

data class SoldTicketListResponse(
    var ticketBody: TicketBody,
    var count: Int,
    var status: String,
    var message: String
)