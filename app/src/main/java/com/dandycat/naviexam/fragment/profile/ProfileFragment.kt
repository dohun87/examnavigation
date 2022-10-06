package com.dandycat.naviexam.fragment.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentProfileBinding
import com.dandycat.naviexam.fragment.BaseChildFragment
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.util.Logger
import com.dandycat.naviexam.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment() : BasePrimaryFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val vm : ProfileViewModel by viewModels()

    override fun initWidget() {
        Logger.d("프로필 화면 시작")
        //Login 화면서 로그인을 안하고 넘길 경우에 대한 observing 로직
        val navController = findNavController()
        val currentBackStackEntry = navController.currentBackStackEntry!!
        val saveStateHandle = currentBackStackEntry.savedStateHandle
        saveStateHandle.getLiveData<Boolean>(LOGINSTATUS).observe(currentBackStackEntry, Observer {
            Logger.d("데이터가 있는 상태이다!!")
            if(!it){
                navController.popBackStack()
            }
        })
        
        if(!vm.getLoginStatus()) findNavController().navigate(R.id.action_fragment_profile_to_fragment_login)
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

}