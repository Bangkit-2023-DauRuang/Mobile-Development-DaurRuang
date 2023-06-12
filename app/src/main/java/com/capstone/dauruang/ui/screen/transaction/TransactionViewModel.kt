package com.capstone.dauruang.ui.screen.transaction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.dauruang.data.network.repository.OrdersRepository
import com.capstone.dauruang.data.network.response.Orders
import com.capstone.dauruang.data.network.response.OrdersResponse
import com.capstone.dauruang.data.network.retrofit.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionViewModel (private val repository: OrdersRepository ) : ViewModel() {

    private val _ordersResult = MutableLiveData<List<Orders>>()
    val ordersResult: LiveData<List<Orders>> = _ordersResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        getAllOrdersData()
    }

    fun getAllOrdersData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllOrders()
                response?.let {listOrders ->
//                    val orders = it
                    withContext(Dispatchers.Main) {
                        _ordersResult.value = listOrders
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = e.message
                }
            }
        }
    }

    companion object {
        private val TAG = TransactionViewModel::class.java.simpleName
    }
}


//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = repository.getAllOrders()
//                Log.e("response", response.toString())
//                val orders = response.body()?.data
//                Log.e("orders", orders.toString())
//                _ordersResult.postValue(orders)
//                Log.e("ini datanya", ordersResult.value.toString())
//            } catch (e: Exception) {
//                _errorMessage.postValue(e.message)
//                Log.e("Error Cok", ordersResult.value.toString())
////                Log.e("kalau ini gmna ?", repository.getAllOrders().toString())
//            }
//        }
//            runCatching {
//
//            }.onSuccess { orders ->
//                withContext(Dispatchers.Main) {
//
//                }
//            }.onFailure { throwable ->
//                withContext(Dispatchers.Main) {
//                    _errorMessage.value = throwable.message
//                    Log.e("error cok", errorMessage.value.toString())
//                }
//            }
