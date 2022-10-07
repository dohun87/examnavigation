package com.dandycat.naviexam.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dandycat.data.pref.PreferenceModule
import com.dandycat.naviexam.util.DynamicLinkUtil
import com.dandycat.naviexam.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val prefModule : PreferenceModule,
    private val dynamicLinkUtil: DynamicLinkUtil
) : ViewModel(){

    fun getLoginName() = prefModule.getLoginName()
    fun setLoginName(userId : String) = prefModule.setLoginName(userId)

    private val _mDynamicLink = MutableLiveData<Event<Uri?>>()
    val mDynamicLink : LiveData<Event<Uri?>> get() = _mDynamicLink

    fun createDynamicLink() {

        viewModelScope.launch {
            prefModule.getLoginName()?.let{
                val key = "profile"
                dynamicLinkUtil.createDynamicLink(key,it){ result ->
                    _mDynamicLink.postValue(Event(result))
                }
            }
        }
    }
}