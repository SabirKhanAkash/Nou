/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.model

data class User(
    var _id: String? = null,
    var phone_no: String? = null,
    var otp: String? = null,
    var username: String? = null,
    var role: String? = null,
    var village: String? = null,
    var dob: String? = null,
    var created_date: String? = null,
    var created_by: String? = null,
    var updated_date: String? = null,
    var updated_by: String? = null,
    var data_source: String? = null,
    var approval_status: String? = null,
    var app_version: String? = null,
)
