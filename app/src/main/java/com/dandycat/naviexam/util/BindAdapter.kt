package com.dandycat.naviexam.util

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("userName")
fun TextView.setUserName(userName : String?) {
    text = "${userName}의 프로필"
}