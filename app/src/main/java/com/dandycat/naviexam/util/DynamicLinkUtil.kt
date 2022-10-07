package com.dandycat.naviexam.util

import android.content.Context
import android.net.Uri
import com.google.firebase.dynamiclinks.ShortDynamicLink
import com.google.firebase.dynamiclinks.ktx.*
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class DynamicLinkUtil @Inject constructor(private val mContext : Context) {

    companion object{
        const val URI = "https://dandycat.com/"
        const val PREFIX = "https://naviexam.page.link"
        const val MINUMUMVERSION = 1
    }

    private val packageName by lazy {
        mContext.packageName
    }


    fun createDynamicLink(key : String,value : String, callback: (Uri?) -> Unit){
        Firebase.dynamicLinks.shortLinkAsync(ShortDynamicLink.Suffix.SHORT){
            link = Uri.parse(URI).buildUpon().appendQueryParameter(key,value).build()
            domainUriPrefix = PREFIX
            androidParameters(packageName) {
                minimumVersion = MINUMUMVERSION
            }
            navigationInfoParameters { // IOS서 실행시키기 위한 로직
                forcedRedirectEnabled = true
            }
            socialMetaTagParameters { // 해당 영역을 설정하여야 카톡등에서 보여진다
                this.title = "$value"
                description = "$value 정보"
            }
        }.addOnSuccessListener {
            val link = it.shortLink
            callback(link)
        }.addOnFailureListener {
            callback(null)
        }
    }
}