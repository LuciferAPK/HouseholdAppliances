package com.example.householdappliances.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Order(
    @SerializedName("createdTime")
    val createdTime: Long? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("totalamount")
    val totalAmount: Int? = 0,
    @SerializedName("totalprice")
    val totalPrice: Long? = 0,
    @SerializedName("payment")
    val payment: String? = null,
    @SerializedName("status")
    var status: String? =null,
    @SerializedName("code")
    var code: String? =null,
    @SerializedName("finalprice")
    var finalPrice: Long? =null,
    @SerializedName("cart")
    var cart: Cart? =null
): Serializable