package com.capstone.dauruang.model

data class User(
    val id: String,
    val username: String?,
    val fullname: String?,
    val email: String,
    val password: String,
    val nohp: String?
) {
    constructor():this("","","","","","")
}