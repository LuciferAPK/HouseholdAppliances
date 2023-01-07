package com.example.householdappliances.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.Cart
import com.example.householdappliances.data.response.ErrorResponse
import com.example.householdappliances.network.Api
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CartRepository @Inject constructor(private val api: Api) {

    fun getCartOfCustomer(
        url: String?,
        id: Int
    ): LiveData<Result<Cart>> =
        liveData(Dispatchers.IO) {
            emit(Result.InProgress())
            try {
                val request = api.getCartOfCustomer(url = url, idCustomer = id)

                if (request.isSuccessful) {
                    emit(Result.Success(request.body() as Cart))
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    emit(Result.Failures(status, request.code(), request.message()))
                }

            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }

    fun addCartItemToCart(
        url: String?,
        cart: Cart
    ): LiveData<Result<Cart>> =
        liveData(Dispatchers.IO) {
            emit(Result.InProgress())
            try {
                val request = api.addCartItemToCart(url = url, cart)

                if (request.isSuccessful) {
                    emit(Result.Success(request.body() as Cart))
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    emit(Result.Failures(status, request.code(), request.message()))
                }

            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }
}