package com.dandycat.naviexam.fragment.login

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentRegCompBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.util.Logger
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
        Logger.d("initWidget - userName : $userName")
        binding.view = this@RegCompFragment
        binding.lifecycleOwner = this@RegCompFragment
    }

    fun removeLoginGraph(){
        with(findNavController()){
            //특정 프래그먼트로의 데이터 전달시 사용
            //getBackStackEntry(R.id.fragment_profile).savedStateHandle.set(LOGINSTATUS,true)
            
            //바로 뒤에 프래그먼트가 있을시 데이터 전달 사용
            previousBackStackEntry?.savedStateHandle?.set(LOGINSTATUS,true)
            popBackStack()
        }
//        findNavController().getBackStackEntry(R.id.fragment_profile).savedStateHandle.set(LOGINSTATUS,true)
//        findNavController().popBackStack()
    }
}