package com.demo.android_mvvm_sample_2025.data.remote.repository

import com.demo.android_mvvm_sample_2025.data.model.PostDto
import com.demo.android_mvvm_sample_2025.data.remote.api.ApiService
import com.demo.android_mvvm_sample_2025.domain.repository.PostRepository
import retrofit2.Response

class PostRepositoryImp(private val apiService: ApiService) : PostRepository{
    override suspend fun getPost(): Response<List<PostDto>> {
            return apiService.getPost()
    }
}