package com.dandycat.naviexam.fragment.notice

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentNoticeBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeFragment() : BasePrimaryFragment<FragmentNoticeBinding>(R.layout.fragment_notice){

    private val TAG = javaClass.simpleName

    var notice : String? = null

    override fun initSetting() {
        binding.view = this@NoticeFragment
        initWidget()
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    override fun initWidget() {
        arguments?.let {
            Log.d(TAG,"데이터가 있다!!")
            notice = it.getString("TEST")
        }
    }
}