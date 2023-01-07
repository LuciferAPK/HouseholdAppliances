package com.example.householdappliances.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Cart(
    @SerializedName("createdTime")
    var createdTime: Long? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var amount: Int? = 0,
    @SerializedName("totalPrice")
    var totalPrice: Long? = 0,
    @SerializedName("cartItems")
    var cartItems: List<CartItem?>? = null,
    @SerializedName("idorder")
    var isOrder: String? =null,
    @SerializedName("customer")
    var idCustomer: String? =null
): Serializable