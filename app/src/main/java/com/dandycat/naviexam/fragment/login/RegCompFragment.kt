package com.dandycat.naviexam.fragment.login

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentRegCompBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegCompFragment() : BasePrimaryFragment<FragmentRegCompBinding>(R.layout.fragment_reg_comp) {

    private val resultArgUserName : RegCompFragmentArgs by navArgs()
    var userName = ""

    override fun initSetting() {
        userName = resultArgUserName.userName
        initWidget()
    }

    override fun onBackPressed() {
        removeLoginGraph()
    }

    override fun initWidget() {
        binding.view = this@RegCompFragment
        binding.lifecycleOwner = this@RegCompFragment
    }

    fun removeLoginGraph(){
        findNavController().popBackStack()
    }
}