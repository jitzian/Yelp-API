package com.example.yelpcode.data.remote

import com.example.yelpcode.data.remote.model.ApiBusiness
import com.example.yelpcode.data.remote.model.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("businesses/search")
    suspend fun fetchBusiness(
        @Query("term") term: String,
        @Query("location") location: String? = "california",
        @Query("limit") limit: Int? = 50,
    ): ApiResult

    @GET("businesses/{id}")
    suspend fun fetchBusinessById(@Path("id") id: String): ApiBusiness

}