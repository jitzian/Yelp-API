package com.example.yelpcode.data.di.modules

import com.example.yelpcode.data.repository.preferences.UserPreferencesRepositoryImpl
import com.example.yelpcode.domain.repository.preferences.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserPreferencesRepositoryModule {

    @Binds
    @Singleton
    abstract fun userPreferencesRepository(
        userPreferencesRepository: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository

}
