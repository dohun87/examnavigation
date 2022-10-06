package com.dandycat.naviexam.fragment.main

import androidx.activity.addCallback
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentMainBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment() : BasePrimaryFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun initWidget() {

    }

    override fun onBackPressed() {
        requireActivity().finish()
    }
}