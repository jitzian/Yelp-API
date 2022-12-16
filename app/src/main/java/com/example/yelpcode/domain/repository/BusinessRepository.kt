package com.example.yelpcode.domain.repository

import com.example.yelpcode.data.remote.model.Businesse
import com.example.yelpcode.data.remote.model.ResultApi

interface BusinessRepository {
    suspend fun fetchBusiness(term: String): ResultApi
    suspend fun fetchBusinessById(id: String): Businesse
}