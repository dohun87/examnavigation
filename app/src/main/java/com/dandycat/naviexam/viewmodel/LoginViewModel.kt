package com.dandycat.naviexam.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dandycat.data.pref.PreferenceModule
import com.dandycat.naviexam.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mPrefModule : PreferenceModule
) : ViewModel() {

    private val _loginSuccess = MutableLiveData<Event<Boolean>>()
    val loginSuccess : LiveData<Event<Boolean>> get() = _loginSuccess

    fun setLogin(){
        mPrefModule.setLoginStatus(true)
        _loginSuccess.postValue(Event(true))
    }
}