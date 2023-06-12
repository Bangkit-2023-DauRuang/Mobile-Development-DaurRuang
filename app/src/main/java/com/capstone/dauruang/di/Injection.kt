package com.capstone.dauruang.di

import android.content.Context
import com.capstone.dauruang.data.network.repository.OrdersRepository
import com.capstone.dauruang.data.network.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): OrdersRepository {
        val apiService = ApiConfig.getApiService()

        return OrdersRepository.getInstance(apiService)
    }
}