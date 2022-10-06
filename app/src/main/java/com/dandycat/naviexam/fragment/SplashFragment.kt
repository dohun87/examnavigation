package com.dandycat.naviexam.fragment

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.dandycat.naviexam.R
import com.dandycat.naviexam.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment() : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private val mHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun init() {
        mHandler.postDelayed({
            findNavController().navigate(R.id.action_fragment_splash_to_fragment_main)
        },3000L)
    }

    override fun backPressed() {
        requireActivity().finishAffinity()
    }
}