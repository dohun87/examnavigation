package com.dandycat.naviexam.viewmodel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dandycat.naviexam.util.Event
import com.dandycat.naviexam.util.Logger
import com.dandycat.naviexam.util.ToastModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegViewModel @Inject constructor(
    private val mToastModule: ToastModule
) : ViewModel() {

    private var inputId = ""

    private val _authValue = MutableLiveData<Event<String>>()
    val authValue : LiveData<Event<String>> get() = _authValue

    fun idInputAfter(s : Editable){
        Logger.d("inputId : $s")
        inputId = s.toString()
    }

    fun checkedAuthUser(){
        if(inputId.isNotEmpty()){
            _authValue.postValue(Event(inputId))
        }else{
            mToastModule.showToast("아이디를 입력해주세요")
        }
    }
}