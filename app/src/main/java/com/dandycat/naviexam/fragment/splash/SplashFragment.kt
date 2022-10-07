package com.dandycat.naviexam.fragment.splash

import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentSplashBinding
import com.dandycat.naviexam.fragment.BaseFragment
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment() : BasePrimaryFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private val mMoveMainFragmentHandler by lazy {
        object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                findNavController().navigate(R.id.action_fragment_splash_to_fragment_main)
            }
        }
    }

    override fun initSetting() {

    }

    override fun onResume() {
        super.onResume()
        if(!mMoveMainFragmentHandler.hasMessages(0)){
            mMoveMainFragmentHandler.sendEmptyMessageDelayed(0,3000L)
        }
    }

    override fun initWidget() {}

    override fun onBackPressed() {
        requireActivity().finish()
    }
}