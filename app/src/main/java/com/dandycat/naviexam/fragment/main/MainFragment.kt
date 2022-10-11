package com.dandycat.naviexam.fragment.main

import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentMainBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.util.Logger
import com.dandycat.naviexam.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment() : BasePrimaryFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val vm : MainViewModel by viewModels()

    override fun initSetting() {
        if(vm.getTutorial()) findNavController().navigate(R.id.action_fragment_main_to_fragment_tutorial)
        else initWidget()
    }

    override fun initWidget() {
        arguments?.let {
            Logger.d("데이터가 있습니다!! data : ${it.getString("TEST")}")
            arguments = null
        } ?: kotlin.run {
            Logger.d("데이터가 없다!!")
        }
    }

    override fun onBackPressed() {
        Logger.d("백키 눌렸다!!")
        requireActivity().finish()
    }
}