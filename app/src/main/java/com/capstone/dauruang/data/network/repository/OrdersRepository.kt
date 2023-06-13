package com.capstone.dauruang.data.network.repository

import android.util.Log
import com.capstone.dauruang.data.network.response.Orders
import com.capstone.dauruang.data.network.response.OrdersResponse
import com.capstone.dauruang.data.network.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject


class OrdersRepository @Inject constructor (private val apiService: ApiService) {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    suspend fun getAllOrders(): Response<OrdersResponse>? {
        return apiService.getAllOrders()
    }


//    suspend fun getAllOrders(): List<Orders> {
//        try {
//            val response = apiService.getAllOrders()
//            if (response.isNotEmpty()) {
//                emit(ApiResponse.Success(response))
//            } else {
//                emit(ApiResponse.Empty)
//            }
//        } catch (e: Exception) {
//            emit(ApiResponse.Error(e.toString()))
//            Log.e("RemoteDataSource", e.toString())
//        }
//    }

    companion object {
        private val TAG = OrdersRepository::class.java.simpleName
        private var INSTANCE: OrdersRepository? = null

        fun getInstance(
            apiService: ApiService,
        ): OrdersRepository {
            return INSTANCE ?: synchronized(this) {
                OrdersRepository(apiService).also {
                    INSTANCE = it
                }
            }
        }
    }



}