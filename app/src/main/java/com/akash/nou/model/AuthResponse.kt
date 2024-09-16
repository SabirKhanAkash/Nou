/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.model

data class AuthResponse(
    var status: String,
    var message: String,
    var accessToken: String? = null,
    var refreshToken: String? = null,
    var user: User? = null
)