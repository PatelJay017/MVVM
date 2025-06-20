package com.demo.android_mvvm_sample_2025.core.extension

import android.view.View

fun View.isShow(){
    this.visibility = View.VISIBLE
}

fun View.isHide(){
    this.visibility = View.GONE
}