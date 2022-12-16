package com.example.yelpcode.data.di.modules

import com.example.yelpcode.data.repository.business.BusinessRepositoryImpl
import com.example.yelpcode.domain.repository.BusinessRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BusinessRepositoryModule {

    @Binds
    @Singleton
    abstract fun businessRepository(
        businessRepository: BusinessRepositoryImpl
    ): BusinessRepository

}