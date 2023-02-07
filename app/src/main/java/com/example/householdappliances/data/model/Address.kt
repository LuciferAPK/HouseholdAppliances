package com.example.householdappliances.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("description")
    var description: Any? = null,
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
): Serializable{
    override fun toString(): String {
        return "Số: $number \nĐường: $street \nPhường: $ward \nQuận: $district \nThành phố: $city"
    }
}