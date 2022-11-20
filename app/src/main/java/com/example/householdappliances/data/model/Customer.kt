package com.example.householdappliances.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Customer(
    @SerializedName("address")
    var address: Address? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("gender")
    var gender: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("password")
    var password: String? = null,
    @SerializedName("tel")
    var tel: String? = null
): Serializable