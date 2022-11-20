package com.example.householdappliances.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
    @SerializedName("category")
    var category: Category? = null,
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("price")
    var price: Int? = 0,
    @SerializedName("quantity")
    var quantity: Int? = 0,
    @SerializedName("unit")
    var unit: String? = ""
): Serializable