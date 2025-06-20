package com.demo.android_mvvm_sample_2025.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.android_mvvm_sample_2025.core.extension.isHide
import com.demo.android_mvvm_sample_2025.core.extension.isShow
import com.demo.android_mvvm_sample_2025.data.remote.api.ApiService
import com.demo.android_mvvm_sample_2025.data.remote.repository.PostRepositoryImp
import com.demo.android_mvvm_sample_2025.data.remote.retrofit.RetrofitClient
import com.demo.android_mvvm_sample_2025.databinding.ActivityMainBinding
import com.demo.android_mvvm_sample_2025.domain.repository.PostRepository
import com.demo.android_mvvm_sample_2025.ui.adapters.PostItemAdapter
import com.demo.android_mvvm_sample_2025.ui.viewmodel.MainViewModel
import com.demo.android_mvvm_sample_2025.ui.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var postAdapter: PostItemAdapter = PostItemAdapter(arrayListOf())
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        val service = RetrofitClient.getRetrofitClient().create(ApiService::class.java)
        val repo : PostRepository = PostRepositoryImp(service)
        MainViewModelFactory(repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeAdapter()
        handleUIState()
    }

    private fun initializeAdapter() {
        binding.dataRecyclerView.adapter = postAdapter
    }

    private fun handleUIState() {
        lifecycleScope.launch {
            mainViewModel.uiState.collect { result ->

                when (result) {
                    is MainViewModel.UIStates.Loading -> {
                        binding.apply {
                            if (result.isShowing) loaderView.isShow() else loaderView.isHide()
                        }
                    }

                    is MainViewModel.UIStates.Success -> {
                        val dataList = result.data?: listOf()
                        postAdapter.submitList(dataList)
                    }

                    is MainViewModel.UIStates.Error -> {
                        Toast.makeText(this@MainActivity, result.errorMessage, Toast.LENGTH_SHORT)
                            .show()
                    }

                    MainViewModel.UIStates.None -> {}
                }
            }
        }
    }
}