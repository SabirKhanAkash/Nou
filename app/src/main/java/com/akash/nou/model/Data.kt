/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.model

import com.akash.nou.dto.AllLookUpDto
import com.akash.nou.dto.TicketLookUpDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("ticketList")
    var ticketList: List<TicketLookUpDto>? = emptyList(),
    @SerialName("allLookUpList")
    var allLookUpList: List<AllLookUpDto>? = emptyList(),
    @SerialName("count")
    var count: Int? = null,
    @SerialName("totalPages")
    var totalPages: Int? = null,
    @SerialName("status")
    var status: String? = null,
)