package com.example.yelpcode.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiRegion(
    @SerializedName("center")
    val apiCenter: ApiCenter?
)