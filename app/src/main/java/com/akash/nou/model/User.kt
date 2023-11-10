/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
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
