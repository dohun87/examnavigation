package com.dandycat.naviexam.fragment.main

import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentMainBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.util.Logger
import com.dandycat.naviexam.viewmodel.MainActivityViewModel
import com.dandycat.naviexam.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment() : BasePrimaryFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val vm : MainViewModel by viewModels()
    private val mainVm : MainActivityViewModel by activityViewModels()

    override fun initSetting() {
        if(!vm.getTutorial()) { // 만약 튜토리얼 일 경우에 대한 동작 적용한다.
            val navController = findNavController()
            val currentBackStackEntry = navController.currentBackStackEntry!!
            val saveStateHandle = currentBackStackEntry.savedStateHandle
            saveStateHandle.getLiveData<Boolean>(TUTORIALEND).observe(currentBackStackEntry, Observer {
                Logger.d("튜토리얼 화면서 나오게 되었다")
                if(it) {
                    Logger.d("튜토리얼에 대해 다음부터는 호출되지 않는다")
                    vm.setTutorial(it)
                }
            })
            navController.navigate(R.id.action_fragment_main_to_fragment_tutorial)
        }
        else initWidget()
    }

    override fun initWidget() {
        mainVm.appLink.observe(viewLifecycleOwner){
            it?.let {
                findNavController().navigate(it)
                mainVm.setAppLink(null)
            }
        }
    }

    override fun onBackPressed() {
        Logger.d("백키 눌렸다!!")
        requireActivity().finish()
    }
}