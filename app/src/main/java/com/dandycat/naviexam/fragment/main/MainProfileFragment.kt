package com.dandycat.naviexam.fragment.main

import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentMainProfileBinding
import com.dandycat.naviexam.fragment.BaseChildFragment
import com.dandycat.naviexam.util.Logger

class MainProfileFragment() : BaseChildFragment<FragmentMainProfileBinding>(R.layout.fragment_main_profile) {
    override fun initWidget() {
        Logger.d("프로필 화면 시작")
    }
}