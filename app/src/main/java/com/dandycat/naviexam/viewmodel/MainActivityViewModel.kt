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

    fun getLoginName() = prefModule.getLoginName()
    fun setLoginName(userId : String) = prefModule.setLoginName(userId)

}