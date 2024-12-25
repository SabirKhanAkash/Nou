/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllLookUpDto(
    @SerialName("_id")
    var _id: String? = null,
    @SerialName("seatCategory")
    var seatCategory: String? = null,
    @SerialName("type")
    var type: String? = null,
    @SerialName("title")
    var title: String? = null,
    @SerialName("title_bn")
    var title_bn: String? = null,
    @SerialName("pageNo")
    var pageNo: Int = 1,
    @SerialName("perPage")
    var perPage: Int = 20,
    @SerialName("orderValue")
    var orderValue: Int = 0,
    @SerialName("vendorId")
    var vendorId: String? = null,
)