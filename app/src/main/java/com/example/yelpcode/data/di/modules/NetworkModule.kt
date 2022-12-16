package com.example.yelpcode.data.di.modules

import com.example.yelpcode.constants.GlobalConstants
import com.example.yelpcode.constants.GlobalConstants.Companion.API_KEY
import com.example.yelpcode.constants.GlobalConstants.Companion.AUTH_HEADER
import com.example.yelpcode.data.remote.RestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton //Scope of the instance
    fun providesRetrofit(): RestApi {
        return Retrofit.Builder()
            .baseUrl(GlobalConstants.BASE_URL)
            .client(
                OkHttpClient()
                    .newBuilder()
                    .addInterceptor { chain ->
                        chain.proceed(
                            chain
                                .request()
                                .newBuilder()
                                .addHeader(AUTH_HEADER, "BEARER $API_KEY")
                                .build()
                        )
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestApi::class.java)
    }
}
