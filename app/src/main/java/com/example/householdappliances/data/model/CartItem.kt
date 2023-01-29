package com.example.householdappliances.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CartItem(
    @SerializedName("id")
    var id: Int? =null,
    @SerializedName("amount")
    var amount: Int =0,
    @SerializedName("createdTime")
    var createdTime: Long? = null,
    var cart: Cart?= null,
    @SerializedName("item")
    var item: Item?= null
): Serializable