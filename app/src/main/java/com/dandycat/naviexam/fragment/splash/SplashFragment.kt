package com.dandycat.naviexam.fragment.splash

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentSplashBinding
import com.dandycat.naviexam.fragment.BaseFragment
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment() : BasePrimaryFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private val mHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun initWidget() {
        mHandler.postDelayed({
            findNavController().navigate(R.id.action_fragment_splash_to_fragment_main)
        },3000L)
    }

    override fun onBackPressed() {
        requireActivity().finish()
    }
}