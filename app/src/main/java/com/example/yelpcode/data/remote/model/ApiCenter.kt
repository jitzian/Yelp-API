package com.example.yelpcode.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiCenter(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)