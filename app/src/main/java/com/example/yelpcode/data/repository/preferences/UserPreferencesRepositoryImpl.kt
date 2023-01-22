package com.example.yelpcode.data.repository.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.yelpcode.domain.repository.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    UserPreferencesRepository {

    companion object {
        val INDEX_PREF = stringPreferencesKey("INDEX_PREF")
    }

    override suspend fun saveIndexListPreferences(indexList: String) {
        dataStore.edit { preferences ->
            preferences[INDEX_PREF] = indexList
        }
    }

    override suspend fun getIndexListPreferences() = dataStore.data.map { data ->
        data[INDEX_PREF] ?: "NOTHING"
    }

}