package com.example.householdappliances.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.Category
import com.example.householdappliances.data.model.Customer
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.data.response.ErrorResponse
import com.example.householdappliances.network.Api
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
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

}