/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.model

data class AuthResponse(
    var status: String,
    var message: String,
    var authToken: String? = null,
    var refreshToken: String? = null,
    var user: User? = null
)