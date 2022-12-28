package com.example.yelpcode.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiLocation(
    @SerializedName("address1")
    val address1: String?,
    @SerializedName("address2")
    val address2: String?,
    @SerializedName("address3")
    val address3: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("zip_code")
    val zipCode: String?
)