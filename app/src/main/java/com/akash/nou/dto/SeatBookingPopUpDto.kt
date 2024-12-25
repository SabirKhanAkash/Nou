package com.akash.nou.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeatBookingPopUpDto(
    @SerialName("isSeatViewPoppedUp")
    var isSeatViewPoppedUp: Boolean = false,
    @SerialName("selectedDate")
    var selectedDate: String? = null,
    @SerialName("selectedTime")
    var selectedTime: String? = null,
    @SerialName("selectedSeatType")
    var selectedSeatType: String? = null,
    @SerialName("selectedSource")
    var selectedSource: String? = null,
    @SerialName("selectedDestination")
    var selectedDestination: String? = null,
    @SerialName("passengerCount")
    var passengerCount: Int? = 1,
    @SerialName("childPassengerCount")
    var childPassengerCount: Int? = 1,
    @SerialName("numberOfColumns")
    var numberOfColumns: Int? = 1,
    @SerialName("seats")
    var seats: List<TicketLookUpDto> = emptyList()
)