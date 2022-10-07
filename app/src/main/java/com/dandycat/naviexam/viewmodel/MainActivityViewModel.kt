package com.dandycat.naviexam.viewmodel

import androidx.lifecycle.ViewModel
import com.dandycat.data.pref.PreferenceModule
import com.dandycat.naviexam.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val prefModule : PreferenceModule
) : ViewModel(){

    fun setLogin(userId : String){
        if(inputId.isNotEmpty()){
            mPrefModule.setLoginName(userId)
            _loginName.postValue(Event(userId))
        }else{
            mToastModule.showToast("아이디를 입력해주세요")
        }
    }

    fun getLoginName() = prefModule.getLoginName()
}