package com.capstone.dauruang.data.network.retrofit

import com.capstone.dauruang.data.network.request.OrderTransactionRequest
import com.capstone.dauruang.data.network.response.OrderTransactionResponse
import com.capstone.dauruang.data.network.response.Orders
import com.capstone.dauruang.data.network.response.OrdersResponse
import com.capstone.dauruang.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("orders")
    suspend fun getAllOrders(): Response<OrdersResponse>

    @POST("orders")
    suspend fun createOrder(@Body request: OrderTransactionRequest): Response<OrderTransactionResponse>
}