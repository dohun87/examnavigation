package com.dandycat.naviexam.fragment.main

import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentMainBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment() : BasePrimaryFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val vm : MainViewModel by viewModels()

    override fun initSetting() {
        if(vm.getTutorial()) findNavController().navigate(R.id.action_fragment_main_to_fragment_tutorial)
    }

    override fun initWidget() {}

    override fun onBackPressed() {
        requireActivity().finish()
    }
}