package com.example.householdappliances.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("name")
    val image: String? = null
) : Serializable