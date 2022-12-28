package com.example.yelpcode.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiResult(
    @SerializedName("businesses")
    val businesses: List<ApiBusiness> = emptyList(),
    @SerializedName("region")
    val apiRegion: ApiRegion?? = null,
    @SerializedName("total")
    val total: Int? = -1
)