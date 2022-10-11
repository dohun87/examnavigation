package com.dandycat.naviexam.fragment.profile

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentProfileOtherBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.viewmodel.MainActivityViewModel
import com.dandycat.naviexam.viewmodel.OtherProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherProfileFragment() : BasePrimaryFragment<FragmentProfileOtherBinding>(R.layout.fragment_profile_other) {

    private val vm : OtherProfileViewModel by viewModels()
    private val mainVm : MainActivityViewModel by activityViewModels()

    private val args : OtherProfileFragmentArgs by navArgs()

    override fun initSetting() {
        binding.vm = vm
        initWidget()
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    override fun initWidget() {
        vm.setUserName(args.userName)
    }
}