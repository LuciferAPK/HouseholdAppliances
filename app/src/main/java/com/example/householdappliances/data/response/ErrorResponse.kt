package com.example.householdappliances.data.response

import com.example.householdappliances.data.model.Status
import com.google.gson.annotations.SerializedName

class ErrorResponse {
    @SerializedName("status")
    val status: Status? = null
}