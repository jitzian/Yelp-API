package com.example.yelpcode.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiCategory(
    @SerializedName("alias")
    val alias: String,
    @SerializedName("title")
    val title: String
)