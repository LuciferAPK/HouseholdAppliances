package com.example.householdappliances.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.householdappliances.base.Result
import com.example.householdappliances.data.model.Category
import com.example.householdappliances.data.model.Item
import com.example.householdappliances.data.response.ErrorResponse
import com.example.householdappliances.network.Api
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val api: Api) {
    fun getAllCategories(
        url: String?
    ): LiveData<Result<List<Category>>> =
        liveData(Dispatchers.IO) {
            emit(Result.InProgress())
            try {
                val request = api.getAllCategory(url = url)

                if (request.isSuccessful) {
                    emit(Result.Success(request.body() as List<Category>))
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    emit(Result.Failures(status, request.code(), request.message()))
                }

            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }

    fun getItemByCategories(
        url: String?,
        idCategory: Int
    ): LiveData<Result<List<Item>>> =
        liveData(Dispatchers.IO) {
            emit(Result.InProgress())
            try {
                val request = api.getItemByCategory(url = url, idCategory = idCategory)

                if (request.isSuccessful) {
                    emit(Result.Success(request.body() as List<Item>))
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