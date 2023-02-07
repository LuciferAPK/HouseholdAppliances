package com.example.householdappliances.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.Customer
import com.example.householdappliances.data.model.Order
import com.example.householdappliances.data.response.ErrorResponse
import com.example.householdappliances.network.Api
import com.google.gson.Gson
import kotlinx.coroutines.*
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: Api
) {

    fun getAllAccountLogin(
        url: String?,
        username: String?,
        password: String?
    ): LiveData<Result<Customer>> =
        liveData(Dispatchers.IO) {
            emit(Result.InProgress())
            try {
                val request = api.checkLogin(url = url, username, password)

                if (request.isSuccessful) {
                    emit(Result.Success(request.body() as Customer))
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    emit(Result.Failures(status, request.code(), request.message()))
                }
            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }

    fun createAccount(
        url: String?,
        customer: Customer
    ): LiveData<Result<Customer>> =
        liveData(Dispatchers.IO) {
            emit(Result.InProgress())
            try {
                val request = api.createAccount(url = url, customer)

                if (request.isSuccessful) {
                    emit(Result.Success(request.body() as Customer))
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    emit(Result.Failures(status, request.code(), request.message()))
                }
            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }

    fun getAllOrderOfCustomer(
        url: String?,
        idCustomer: Int?
    ): LiveData<Result<List<Order>>> {
        val orderLiveData = MutableLiveData<Result<List<Order>>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            orderLiveData.postValue(Result.Failure(400, throwable.message))
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            orderLiveData.postValue(Result.InProgress())
            val request = api.getAllOrderOfCustomer(url = url, idCustomer = idCustomer)
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    val orderResponse = Result.Success(request.body() as List<Order>)
                    orderLiveData.postValue(orderResponse)
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