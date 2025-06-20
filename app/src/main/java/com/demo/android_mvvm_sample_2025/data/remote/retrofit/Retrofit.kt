package com.demo.android_mvvm_sample_2025.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val BASE = "https://jsonplaceholder.typicode.com/"

    fun getRetrofitClient() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}