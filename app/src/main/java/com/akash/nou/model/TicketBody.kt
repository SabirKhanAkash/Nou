package com.akash.nou.model

data class TicketBody(
    var seat_category: String,
    var source: String,
    var destination: String,
    var journeyDateTime: String,
    var adultItemCount: Int
)
