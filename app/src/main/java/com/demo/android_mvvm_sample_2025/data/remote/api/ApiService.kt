package com.demo.android_mvvm_sample_2025.data.remote.api

import com.demo.android_mvvm_sample_2025.data.model.PostDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPost() : Response<List<PostDto>>
}