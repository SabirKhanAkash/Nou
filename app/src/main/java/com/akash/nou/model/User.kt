/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("_id")
    var _id: String? = null,
    @SerialName("phone_no")
    var phone_no: String? = null,
    @SerialName("otp")
    var otp: String? = null,
    @SerialName("username")
    var username: String? = null,
    @SerialName("role")
    var role: String? = null,
    @SerialName("village")
    var village: String? = null,
    @SerialName("dob")
    var dob: String? = null,
    @SerialName("created_date")
    var created_date: String? = null,
    @SerialName("created_by")
    var created_by: String? = null,
    @SerialName("updated_date")
    var updated_date: String? = null,
    @SerialName("updated_by")
    var updated_by: String? = null,
    @SerialName("data_source")
    var data_source: String? = null,
    @SerialName("approval_status")
    var approval_status: String? = null,
    @SerialName("app_version")
    var app_version: String? = null,
)
