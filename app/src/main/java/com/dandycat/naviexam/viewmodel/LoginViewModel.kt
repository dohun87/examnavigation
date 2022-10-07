package com.dandycat.naviexam.viewmodel

import android.os.Handler
import android.os.Looper
import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dandycat.data.pref.PreferenceModule
import com.dandycat.naviexam.util.Event
import com.dandycat.naviexam.util.Logger
import com.dandycat.naviexam.util.ToastModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mPrefModule : PreferenceModule,
    private val mToastModule: ToastModule
) : ViewModel() {

    private val _loginSuccess = MutableLiveData<Event<Boolean>>()
    val loginSuccess : LiveData<Event<Boolean>> get() = _loginSuccess

    private var inputId = ""

    fun idInputAfter(s : Editable){
        Logger.d("inputId : $s")
        inputId = s.toString()
    }

    fun setLogin(){
        if(inputId.isNotEmpty()){
            mPrefModule.setLoginStatus(true)
            _loginSuccess.postValue(Event(true))
        }else{
            mToastModule.showToast("아이디를 입력해주세요")
        }
    }
}