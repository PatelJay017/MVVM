package com.demo.android_mvvm_sample_2025.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.android_mvvm_sample_2025.data.model.PostDto
import com.demo.android_mvvm_sample_2025.databinding.UserItemBinding

class PostItemAdapter(private var dataList: List<PostDto>) :
    RecyclerView.Adapter<PostItemAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]

        holder.binding.apply {
            txtTitle.text = data.title
            txtBody.text = data.body
        }
    }

    fun submitList(dataList: List<PostDto>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }
}