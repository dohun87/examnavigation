package com.dandycat.naviexam.fragment.login

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentRegBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.util.Logger
import com.dandycat.naviexam.util.SingleEventObserver
import com.dandycat.naviexam.viewmodel.MainActivityViewModel
import com.dandycat.naviexam.viewmodel.RegViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegFragment() : BasePrimaryFragment<FragmentRegBinding>(R.layout.fragment_reg) {

    private val regVm : RegViewModel by viewModels()
    //해당 뷰모델에서는 아이디를 가입이 될 경우 저장 시키게 해준다
    private val mainVm : MainActivityViewModel by activityViewModels()

    override fun initSetting() {
        regVm.authValue.observe(viewLifecycleOwner,SingleEventObserver{
            Logger.d("inputId : $it")
            moveRegCompFragment(it)
        })
        initWidget()
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    override fun initWidget() {
        binding.vm = regVm
    }

    private fun moveRegCompFragment(userName : String){
        mainVm.setLoginName(userName)
        val action = RegFragmentDirections.actionFragmentRegToFragmentRegComp(userName)
        findNavController().navigate(action)
    }
}