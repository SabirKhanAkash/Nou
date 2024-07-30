package com.akash.nou.dto

class SeatBookingPopUpDTO {
    var isSeatViewPoppedUp: Boolean = false
    lateinit var selectedDate: String
    lateinit var selectedTime: String
    lateinit var selectedSeatType: String
    lateinit var selectedSource: String
    lateinit var selectedDestination: String
    var passengerCount: Int = 0
    var childPassengerCount: Int = 0
    var numberOfColumns: Int = 6
    var seats: List<TicketLookUpDTO> = emptyList()
}
