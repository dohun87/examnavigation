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
    //SafeArg로 이용해서 전달 예제를 쓸거기 때문에 해당 ViewModel은 사용하지 않는다.
    //private val mainVm : MainActivityViewModel by activityViewModels()

    override fun initSetting() {
        regVm.authValue.observe(viewLifecycleOwner,SingleEventObserver{
            Logger.d("inputId : $it")
        })
        initWidget()
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    override fun initWidget() {
        binding.vm = regVm
    }
}