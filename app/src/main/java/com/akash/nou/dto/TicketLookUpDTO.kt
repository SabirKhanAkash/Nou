/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketLookUpDto(
    @SerialName("toRefresh")
    var toRefresh: Boolean = false,
    @SerialName("seatNumber")
    var seatNumber: String? = null,
    @SerialName("seatCategory")
    var seatCategory: String? = null,
    @SerialName("source")
    var source: String? = null,
    @SerialName("destination")
    var destination: String? = null,
    @SerialName("date")
    var date: String? = null,
    @SerialName("time")
    var time: String? = null,
    @SerialName("accessToken")
    var accessToken: String? = null,
    @SerialName("refreshToken")
    var refreshToken: String? = null,
    @SerialName("pageNo")
    var pageNo: Int = 1,
    @SerialName("passengerCount")
    var passengerCount: Int = 1,
    @SerialName("childPassengerCount")
    var childPassengerCount: Int = 0,
    @SerialName("sold")
    var sold: Boolean = false,
    @SerialName("isSelected")
    var isSelected: Boolean = true,
)