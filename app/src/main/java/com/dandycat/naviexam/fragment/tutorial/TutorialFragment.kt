package com.dandycat.naviexam.fragment.tutorial

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentTutorialBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.util.Logger
import com.dandycat.naviexam.viewmodel.TutorialViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialFragment() : BasePrimaryFragment<FragmentTutorialBinding>(R.layout.fragment_tutorial) {

    private val vm : TutorialViewModel by viewModels()

    override fun initSetting() {

    }

    override fun initWidget() {

    }

    override fun onBackPressed() {
        //이전 프래그먼트로 데이터 전달시 사용
        findNavController().previousBackStackEntry?.savedStateHandle?.set(TUTORIALEND,true)
        Logger.d("튜토리얼 종료 데이터 전달")
        //특정 프래그먼트로 데이터 전달시 사용
        //getBackStackEntry(R.id.fragment_main).savedStateHandle.set(TUTORIALEND,true)
        findNavController().popBackStack()
    }
}