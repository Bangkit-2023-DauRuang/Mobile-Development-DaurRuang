package com.capstone.dauruang.ui.screen.transaction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.dauruang.data.network.repository.OrdersRepository
import com.capstone.dauruang.data.network.request.OrderTransactionRequest
import com.capstone.dauruang.data.network.response.OrderTransactionData
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

    private val _ordersUserResult = MutableLiveData<List<Orders>>()
    val ordersUserResult: LiveData<List<Orders>> = _ordersUserResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _orderResultTransaction = MutableLiveData<OrderTransactionData>()
    val orderResultTransaction: LiveData<OrderTransactionData> = _orderResultTransaction

    init {
        getAllOrdersData()
    }

    fun getAllOrdersData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllOrders()
                response?.let {listOrders ->
                    withContext(Dispatchers.Main) {
                        _ordersResult.value = listOrders.body()?.data
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = e.message
                }
            }
        }
    }

    fun getUserOrdersData(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getOrdersByEmail(email)
                response?.let {listOrders ->
                    withContext(Dispatchers.Main) {
                        _ordersUserResult.value = listOrders.body()?.data
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = e.message
                }
            }
        }
    }

    fun createOrder(request: OrderTransactionRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.createOrder(request)
                if (response != null) {
                    if (response.isSuccessful) {
                        val orderData = response.body()?.data
                        withContext(Dispatchers.Main) {
                            _orderResultTransaction.value = orderData
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = parseErrorMessage(errorBody)
                        withContext(Dispatchers.Main) {
                            _errorMessage.value = errorMessage
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = e.message
                }
            }
        }
    }

    private fun parseErrorMessage(errorBody: String?): String {
        return ""
    }

    companion object {
        private val TAG = TransactionViewModel::class.java.simpleName
    }
}

