package com.dandycat.data.pref

import android.content.Context
import javax.inject.Inject

class PreferenceModule @Inject constructor(private val context : Context) {

    companion object {
        private const val PREF_NAME = "navi_pref"
        private const val TUTORIAL = "tutorial"
        private const val LOGIN = "login"
        private const val NAME ="name"
    }

    private val pref by lazy {
        context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
    }

    private val mEditor by lazy {
        pref.edit()
    }

    fun setTutorialStatus(tutorialEnd : Boolean) = mEditor.putBoolean(TUTORIAL,tutorialEnd).commit()
    fun getTutorialStatus() : Boolean = pref.getBoolean(TUTORIAL,true)

    fun setLoginName(name : String?) = mEditor.putString(NAME,name).commit()
    fun getLoginName() = pref.getString(NAME,null)
}