package com.dandycat.naviexam.fragment.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentLoginBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.util.SingleEventObserver
import com.dandycat.naviexam.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BasePrimaryFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val vm : LoginViewModel by viewModels()

    override fun initSetting() {
        binding.vm = vm
        vm.loginSuccess.observe(viewLifecycleOwner,SingleEventObserver{
            setPreviousNavigationDataHandle(it)
            findNavController().popBackStack()
        })
        setPreviousNavigationDataHandle(false)
    }

    override fun initWidget() {}

    override fun onBackPressed() {
        setPreviousNavigationDataHandle(false)
        findNavController().popBackStack()
    }

    private fun setPreviousNavigationDataHandle(isSuccess : Boolean){
        findNavController().previousBackStackEntry?.savedStateHandle?.set(LOGINSTATUS,isSuccess)

    }


}