/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    @SerialName("authSource")
    var authSource: String? = null,
    @SerialName("phoneNo")
    var phoneNo: String? = null,
    @SerialName("otp")
    var otp: String? = null,
)