package com.dandycat.naviexam.fragment.profile

import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentProfileBinding
import com.dandycat.naviexam.fragment.BaseChildFragment
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.util.Logger

class ProfileFragment() : BasePrimaryFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    override fun initWidget() {
        Logger.d("프로필 화면 시작")
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

}