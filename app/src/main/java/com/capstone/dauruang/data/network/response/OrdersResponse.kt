package com.capstone.dauruang.data.network.response

import com.google.gson.annotations.SerializedName

data class Orders(
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("username")
    val username: String?,

    @field:SerializedName("jenis_sampah")
    val jenis_sampah: String?,

    @field:SerializedName("hargaPerKg")
    val hargaPerKg: Int?,

    @field:SerializedName("berat_sampah")
    val berat_sampah: Int?,

    @field:SerializedName("points")
    val points: Int?,

    @field:SerializedName("lokasi_pengepul")
    val lokasi_pengepul: String?,

    @field:SerializedName("lokasi_user")
    val lokasi_user: String?,

    @field:SerializedName("catatan")
    val catatan: String?,

    @field:SerializedName("status_pemesanan")
    val status_pemesanan: String?,

    @field:SerializedName("createdAt")
    val createdAt: String?,

    @field:SerializedName("updatedAt")
    val updatedAt: String?
)


data class OrdersResponse(
    val data: List<Orders>
)

// Response for post order
data class OrderTransactionResponse(
    val message: String,
    val data: OrderTransactionData
)

data class OrderTransactionData(
    val status_pemesanan: String,
    val id: Int,
    val username: String,
    val jenis_sampah: String,
    val hargaPerKg: Int,
    val berat_sampah: Int,
    val points: Int,
    val lokasi_pengepul: String,
    val lokasi_user: String,
    val catatan: String,
    val updatedAt: String,
    val createdAt: String
)
