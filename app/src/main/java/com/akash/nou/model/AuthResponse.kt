/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerialName("status")
    var status: String,
    @SerialName("message")
    var message: String,
    @SerialName("accessToken")
    var accessToken: String? = null,
    @SerialName("refreshToken")
    var refreshToken: String? = null,
    @SerialName("user")
    var user: User? = null
)