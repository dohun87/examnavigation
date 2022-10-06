package com.dandycat.naviexam.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : ViewDataBinding>(private val layoutResId : Int) : Fragment() {

    abstract fun init()
    abstract fun backPressed()
    lateinit var binding: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,layoutResId,container,false)
        activity?.onBackPressedDispatcher?.addCallback {
            backPressed()
        }

        init()
        return binding.root
    }
}