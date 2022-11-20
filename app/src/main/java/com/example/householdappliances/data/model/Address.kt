package com.example.householdappliances.data.model


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("decripsiton")
    var decripsiton: Any? = null,
    @SerializedName("district")
    var district: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("number")
    var number: String? = null,
    @SerializedName("street")
    var street: String? = null,
    @SerializedName("ward")
    var ward: String? = null
)