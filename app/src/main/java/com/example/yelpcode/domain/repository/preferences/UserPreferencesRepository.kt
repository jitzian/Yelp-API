package com.example.yelpcode.domain.repository.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun saveIndexListPreferences(indexList: String)
    suspend fun getIndexListPreferences(): Flow<String>
}