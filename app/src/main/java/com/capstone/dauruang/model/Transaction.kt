package com.capstone.dauruang.model

import java.util.Date

data class Order(
    val id: Int,
    val weight: Int,
    val point: Int,
    val price: String,
    val status: String,
    val loc_user: String,
    val loc_daurulang: String,
    val date: Date,
    val note: String
)

data class Transaction(
    val id: Int,
    val weight: Int,
    val point: Int,
    val price: String,
    val status: String,
    val loc_user: List<Location>,
    val loc_daurulang: List<Location>,
    val date: Date,
    val note: String
)

data class Location(
    val lat: String,
    val lon: String
)




