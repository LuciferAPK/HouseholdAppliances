package com.example.householdappliances.network

import com.example.householdappliances.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {
    @GET
    suspend fun getItemByCategory(
        @Url url: String?,
        @Query("idCategory") idCategory: Int
    ) : Response<List<Item>>

    @GET
    suspend fun  getAllCategory(
        @Url url: String?
    ): Response<List<Category>>

    @GET
    suspend fun checkLogin(
        @Url url: String?,
        @Query("username") username : String?,
        @Query("password") password : String?
    ): Response<Customer>

    @GET
    suspend fun searchItemByKey(
        @Url url: String?,
        @Query("key") key: String?
    ): Response<List<Item>>

    @GET
    suspend fun getCartOfCustomer(
        @Url url: String?,
        @Query("idCustomer") idCustomer: Int
    ): Response<Cart>

    @POST
    suspend fun addCartItemToCart(
        @Url url: String?,
        @Body cart: Cart?
    ): Response<Cart>

    @POST
    suspend fun createAccount(
        @Url url: String?,
        @Body customer: Customer
    ): Response<Customer>

    @POST
    suspend fun createOrder(
        @Url url: String,
        @Body order: Order
    ): Response<Order>

}