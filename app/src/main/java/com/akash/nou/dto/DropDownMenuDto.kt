package com.akash.nou.dto

import com.akash.nou.R

class DropDownMenuDto {
    lateinit var heading: String
    var leadingIcon: Int = R.drawable.ic_seat
    var items: List<String>? = null
}
