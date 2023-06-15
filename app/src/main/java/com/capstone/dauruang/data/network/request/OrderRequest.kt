package com.capstone.dauruang.data.network.request

import com.google.gson.annotations.SerializedName

data class OrderTransactionRequest(
    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("jenis_sampah")
    val jenis_sampah: String,

    @SerializedName("berat_sampah")
    val berat_sampah: Int,

    @SerializedName("lokasi_pengepul")
    val lokasi_pengepul: String,

    @SerializedName("lokasi_user")
    val lokasi_user: String,

    @SerializedName("catatan")
    val catatan: String
)