package com.example.yelpcode.data.repository.business

import com.example.yelpcode.data.remote.RestApi
import com.example.yelpcode.data.remote.model.Businesse
import com.example.yelpcode.data.remote.model.ResultApi
import com.example.yelpcode.domain.repository.BusinessRepository
import javax.inject.Inject

class BusinessRepositoryImpl @Inject constructor(
    private val restApi: RestApi
) : BusinessRepository {
    override suspend fun fetchBusiness(term: String): ResultApi {
        return restApi.fetchBusiness(term)
    }

    override suspend fun fetchBusinessById(id: String): Businesse {
        return restApi.fetchBusinessById(id)
    }
}