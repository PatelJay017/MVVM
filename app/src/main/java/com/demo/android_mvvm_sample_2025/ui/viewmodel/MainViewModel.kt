package com.demo.android_mvvm_sample_2025.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.demo.android_mvvm_sample_2025.data.model.PostDto
import com.demo.android_mvvm_sample_2025.domain.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(postRepository: PostRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UIStates>(UIStates.Loading(isShowing = true))
    val uiState: StateFlow<UIStates> = _uiState

    init {
        fetchPosts(postRepository)
    }


    private fun fetchPosts(postRepository: PostRepository) {
        viewModelScope.launch {
            try {
                val response = postRepository.getPost()
                if (response.isSuccessful) {
                    _uiState.value = UIStates.Success(response.body())
                } else {
                    _uiState.value = UIStates.Error(response.message())
                }
            } catch (e: Exception) {
                _uiState.value = UIStates.Error(e.message ?: "Something went wrong")
            } finally {
                val current = _uiState.value
                if (current is UIStates.Loading) {
                    _uiState.value = UIStates.Loading(isShowing = false)
                }
            }
        }
    }

    sealed class UIStates {
        data object None : UIStates()
        data class Loading(val isShowing: Boolean) : UIStates()
        data class Success(val data: List<PostDto>?) : UIStates()
        data class Error(val errorMessage: String) : UIStates()
    }
}

class MainViewModelFactory(private val postRepository: PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(postRepository) as T
    }
}