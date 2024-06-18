package com.akash.nou.model

data class Tickets(
    var tickets: Tickets,
    var ticketList: List<TicketBody>,
    var count: Int,
    var status: String,
    var message: String,
    var token: Boolean
)
