package com.example.yelpcode.data.remote.model

import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("center")
    val center: Center?
)