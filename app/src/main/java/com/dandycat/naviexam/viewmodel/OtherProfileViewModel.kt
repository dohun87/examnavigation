package com.dandycat.naviexam.viewmodel

import androidx.lifecycle.ViewModel
import com.dandycat.naviexam.util.DynamicLinkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtherProfileViewModel @Inject constructor(
    private val mDynamicLinkUtil: DynamicLinkUtil
) : ViewModel(){

    var otherUserName = ""

    fun setUserName(userName : String){
        this.otherUserName = userName
    }

    fun createDynamicLink(){

    }
}