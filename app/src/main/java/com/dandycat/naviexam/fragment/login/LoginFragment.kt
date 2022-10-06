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

    override fun initWidget() {
        binding.vm = vm
        vm.loginSuccess.observe(viewLifecycleOwner,SingleEventObserver{
            setPreviosNavigationDataHandle(it)
            findNavController().popBackStack()
        })
        setPreviosNavigationDataHandle(false)
    }

    override fun onBackPressed() {
        setPreviosNavigationDataHandle(false)
        findNavController().popBackStack()
    }

    private fun setPreviosNavigationDataHandle(isSuccess : Boolean){
        findNavController().previousBackStackEntry?.savedStateHandle?.set(LOGINSTATUS,isSuccess)

    }
}