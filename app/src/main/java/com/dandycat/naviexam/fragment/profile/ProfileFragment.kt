package com.dandycat.naviexam.fragment.profile

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentProfileBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.util.DynamicLinkUtil
import com.dandycat.naviexam.util.Logger
import com.dandycat.naviexam.util.SingleEventObserver
import com.dandycat.naviexam.viewmodel.MainActivityViewModel
import com.dandycat.naviexam.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment() : BasePrimaryFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val profileVm : ProfileViewModel by viewModels()
    private val mainVm : MainActivityViewModel by activityViewModels()

    override fun initSetting() {
        Logger.d("프로필 화면 시작")
        //Login 화면서 로그인을 안하고 넘길 경우에 대한 observing 로직 - 해당로직은 실제 다음 프래그먼트 동작서 값이 들어오면 동작 되게 되어 있다.
        //해당 동작에 대해서 체크 시 현재 1회성 동작으로만 진행 되고 있다(로그 확인시)
        val navController = findNavController()
        val currentBackStackEntry = navController.currentBackStackEntry!!
        val saveStateHandle = currentBackStackEntry.savedStateHandle
        saveStateHandle.getLiveData<Boolean>(LOGINSTATUS).observe(currentBackStackEntry, Observer {
            Logger.d("로그인 화면의 결과가 들어왔다!!")
            if(!it){
                Logger.d("로그인 버튼을 누르지 않았기 때문에 메인으로 넘겨준다")
                navController.popBackStack()
            }
        })
        if(mainVm.getLoginName().isNullOrEmpty()){
            Logger.d("로그인 화면으로의 이동!!")
            findNavController().navigate(R.id.move_login)
        }
        else{
            mainVm.logout.observe(viewLifecycleOwner,SingleEventObserver{
                if(it) findNavController().navigate(R.id.move_main)
            })
            initWidget()
            mainVm.mDynamicLink.observe(viewLifecycleOwner,SingleEventObserver{
                it?.let {
                    sharedDynamicLink(it)
                }
            })
        }
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    override fun initWidget() {
        binding.vm = mainVm
    }

    private fun sharedDynamicLink(uri : Uri) {
        Logger.d("profile Share Uri : $uri")
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, uri.toString())
            type = "text/plain"
        }
        startActivity(Intent.createChooser(intent, "프로필 공유"))
    }
}