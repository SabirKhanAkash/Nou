package com.akash.nou.dto

class TicketLookUpDTO {
    lateinit var seatType: String
    lateinit var source: String
    lateinit var destination: String
    lateinit var date: String
    lateinit var time: String
    var passengerCount: Int = 0
    var childPassengerCount: Int = 0
}