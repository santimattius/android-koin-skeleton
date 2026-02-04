package com.santimattius.basic.skeleton.core.networking

import okhttp3.OkHttpClient
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Single(createdAtStart = true)
class RetrofitServiceCreator(@Named("base_url") val baseUrl: String) {

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(ExampleInterceptor())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T : Any> create() = retrofit.create<T>()
}