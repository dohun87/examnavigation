package com.dandycat.naviexam.viewmodel

import androidx.lifecycle.ViewModel
import com.dandycat.data.pref.PreferenceModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mPrefModule : PreferenceModule
) : ViewModel() {

    fun getTutorial() = mPrefModule.getTutorialStatus()
}