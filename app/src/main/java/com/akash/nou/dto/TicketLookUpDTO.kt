package com.akash.nou.dto

class TicketLookUpDTO {
    lateinit var seatNumber: String
    lateinit var seatType: String
    lateinit var source: String
    lateinit var destination: String
    lateinit var date: String
    lateinit var time: String
    var passengerCount: Int = 0
    var isOccupied: Boolean = false
    var isSelected: Boolean = true
    var childPassengerCount: Int = 0
}