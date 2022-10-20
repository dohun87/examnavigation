package com.dandycat.naviexam.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dandycat.data.pref.PreferenceModule
import com.dandycat.naviexam.util.DynamicLinkUtil
import com.dandycat.naviexam.util.Event
import com.dandycat.naviexam.util.ToastModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val prefModule : PreferenceModule,
    private val mToastModule: ToastModule,
    private val dynamicLinkUtil: DynamicLinkUtil
) : ViewModel(){

    fun getLoginName() = prefModule.getLoginName()
    fun setLoginName(userId : String) = prefModule.setLoginName(userId)

    private val _mDynamicLink = MutableLiveData<Event<Uri?>>()
    val mDynamicLink : LiveData<Event<Uri?>> get() = _mDynamicLink

    private val _logout = MutableLiveData<Event<Boolean>>()
    val logout : LiveData<Event<Boolean>> get() = _logout

    //저장하기 위한 AppLink LiveData 선언
    private val _appLink = MutableLiveData<Uri?>()
    val appLink : LiveData<Uri?> get() = _appLink

    fun createDynamicLink() {
        viewModelScope.launch {
            prefModule.getLoginName()?.let{
                val key = "profile"
                dynamicLinkUtil.createDynamicLink(key,it){ result ->
                    result?.let {
                        _mDynamicLink.postValue(Event(result))
                    } ?: kotlin.run {
                        mToastModule.showToast("링크 생성 실패")
                    }
                }
            }
        }
    }

    fun logoutUser(){
        viewModelScope.launch {
            prefModule.setLoginName(null)
            _logout.postValue(Event(true))
        }
    }

    fun setAppLink(uri : Uri?){
        _appLink.postValue(uri)
    }
}