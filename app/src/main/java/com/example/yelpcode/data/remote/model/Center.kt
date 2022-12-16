package com.example.yelpcode.data.remote.model

import com.google.gson.annotations.SerializedName

data class Center(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)