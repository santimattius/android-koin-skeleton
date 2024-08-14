package com.santimattius.basic.skeleton.di

import com.santimattius.basic.skeleton.core.networking.RetrofitServiceCreator
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

@Module
class AppModule {

    @Factory
    @Singleton
    fun provideRetrofitCreator(): RetrofitServiceCreator {
        return RetrofitServiceCreator(baseUrl = "https://www.example.com/api")
    }
}