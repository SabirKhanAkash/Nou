package com.akash.nou.dto

class TicketLookUpDTO {
    lateinit var seatNumber: String
    lateinit var seatCategory: String
    lateinit var source: String
    lateinit var destination: String
    lateinit var date: String
    lateinit var time: String
    var pageNo: Int = 1
    var passengerCount: Int = 0
    var sold: Boolean = false
    var isSelected: Boolean = true
    var childPassengerCount: Int = 0
}