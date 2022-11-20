package com.example.householdappliances.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Cart(
    @SerializedName("createdTime")
    val createdTime: Long? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val amount: Int? = 0,
    @SerializedName("totalPrice")
    val totalPrice: Long? = 0,
    @SerializedName("cartItems")
    val cartItems: List<CartItem?>? = null,
    @SerializedName("idorder")
    var isOrder: String? =null
): Serializable