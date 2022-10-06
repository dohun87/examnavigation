package com.dandycat.naviexam.fragment

import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment() : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    override fun init() {

    }

    override fun backPressed() {
        requireActivity().finishAffinity()
    }
}