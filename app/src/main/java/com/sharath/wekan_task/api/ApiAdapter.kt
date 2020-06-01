package com.sharath.wekan_task.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {

    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //OkhttpClient for building http request url
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .build()


    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://developer.nrel.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}