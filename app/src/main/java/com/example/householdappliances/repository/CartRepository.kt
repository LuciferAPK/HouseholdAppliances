package com.example.householdappliances.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.householdappliances.application.ApplicationContext
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.Cart
import com.example.householdappliances.data.model.Order
import com.example.householdappliances.data.response.ErrorResponse
import com.example.householdappliances.network.Api
import com.google.gson.Gson
import kotlinx.coroutines.*
import javax.inject.Inject

class CartRepository @Inject constructor(private val api: Api) {

    fun getCartOfCustomer(
        url: String?
    ): LiveData<Result<Cart>> {
        val cartLiveData = MutableLiveData<Result<Cart>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            cartLiveData.postValue(Result.Failure(400, throwable.message))
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            cartLiveData.postValue(Result.InProgress())
            val request = api.getCartOfCustomer(url = url, idCustomer = ApplicationContext.customer?.id ?: 0)
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    val cartResponse = Result.Success(request.body() as Cart)
                    cartLiveData.postValue(cartResponse)
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    cartLiveData.postValue(
                        Result.Failures(
                            status,
                            request.code(),
                            request.message()
                        )
                    )
                }
            }
        }
        return cartLiveData
    }

    fun addCartItemToCart(
        url: String?,
        cart: Cart?
    ): LiveData<Result<Cart>> {
        val cartLiveData = MutableLiveData<Result<Cart>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            cartLiveData.postValue(Result.Failure(400, throwable.message))
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            cartLiveData.postValue(Result.InProgress())
            val request = api.addCartItemToCart(url = url, cart = cart)
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    val cartResponse = Result.Success(request.body() as Cart)
                    cartLiveData.postValue(cartResponse)
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    cartLiveData.postValue(
                        Result.Failures(
                            status,
                            request.code(),
                            request.message()
                        )
                    )
                }
            }
        }
        return cartLiveData
    }

    fun takeOrder(
        url: String?,
        order: Order?
    ): LiveData<Result<Order>> {
        val orderLiveData = MutableLiveData<Result<Order>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            orderLiveData.postValue(Result.Failure(400, throwable.message))
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            orderLiveData.postValue(Result.InProgress())
            val request = api.createOrder(url = url, order = order)
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    val cartResponse = Result.Success(request.body() as Order)
                    orderLiveData.postValue(cartResponse)
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    orderLiveData.postValue(
                        Result.Failures(
                            status,
                            request.code(),
                            request.message()
                        )
                    )
                }
            }
        }
        return orderLiveData
    }
}