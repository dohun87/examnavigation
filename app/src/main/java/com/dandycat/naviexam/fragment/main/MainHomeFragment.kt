package com.dandycat.naviexam.fragment.main

import androidx.activity.addCallback
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentMainHomeBinding
import com.dandycat.naviexam.fragment.BaseChildFragment
import com.dandycat.naviexam.fragment.BaseFragment
import com.dandycat.naviexam.util.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHomeFragment() : BaseChildFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home) {

    override fun initWidget() {
        Logger.d("initWidget")
    }


}