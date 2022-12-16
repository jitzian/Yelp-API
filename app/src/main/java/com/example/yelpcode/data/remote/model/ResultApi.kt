package com.example.yelpcode.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResultApi(
    @SerializedName("businesses")
    val businesses: List<Businesse?>,
    @SerializedName("region")
    val region: Region?,
    @SerializedName("total")
    val total: Int?
)