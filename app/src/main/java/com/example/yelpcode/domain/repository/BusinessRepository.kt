package com.example.yelpcode.domain.repository

import com.example.yelpcode.domain.model.BusinessModel

interface BusinessRepository {
    suspend fun fetchBusiness(term: String): MutableList<BusinessModel?>
    suspend fun fetchBusinessById(id: String): BusinessModel
}