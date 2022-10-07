package com.dandycat.naviexam.util

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ToastModule @Inject constructor(private val context : Context){

    fun showToast(msg : String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }
}