package com.capstone.dauruang.data.network.response

import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

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

    @field:SerializedName("isPickedUp")
    val isPickedUp: Boolean?,

    @field:SerializedName("createdAt")
    val createdAt: String?,

    @field:SerializedName("updatedAt")
    val updatedAt: String?
)


data class OrdersResponse(
    val data: List<Orders>
)



//@Query("id") id: Int? = null,
//@Query("username") username: Int? = null,
//@Query("jenis_sampah") jenis_sampah: Int? = null,
//@Query("hargaPerKg") hargaPerKg: Int? = null,
//@Query("points") points: Int? = null,
//@Query("lokasi_pengepul") lokasi_pengepul: String? = null,
//@Query("lokasi_pengepul") lokasi_user: String? = null,
//@Query("catatan") catatan: String? = null,
//@Query("isPickedUp") isPickedUp: Boolean? = null,
//@Query("createdAt") createdAt : String? = null,
//@Query("updatedAt") updatedAt : String? = null