package com.akash.nou.dto

class AllLookUpDto {
    lateinit var seatCategory: String
    lateinit var _id: String
    lateinit var type: String
    lateinit var title: String
    lateinit var title_bn: String
    var perPage: Int = 20
    var pageNo: Int = 1
    var orderValue: Int = 0
    lateinit var vendorId: String
}