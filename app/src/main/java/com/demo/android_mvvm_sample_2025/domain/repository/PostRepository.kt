package com.demo.android_mvvm_sample_2025.domain.repository

import com.demo.android_mvvm_sample_2025.data.model.PostDto
import retrofit2.Response

interface PostRepository {

    suspend fun getPost() : Response<List<PostDto>>
}