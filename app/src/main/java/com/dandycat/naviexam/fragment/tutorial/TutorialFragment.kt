package com.dandycat.naviexam.fragment.tutorial

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentTutorialBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.viewmodel.TutorialViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialFragment() : BasePrimaryFragment<FragmentTutorialBinding>(R.layout.fragment_tutorial) {

    private val vm : TutorialViewModel by viewModels()

    override fun initWidget() {

    }

    override fun onBackPressed() {
        vm.setTutorialEnd()
        findNavController().popBackStack()
    }
}