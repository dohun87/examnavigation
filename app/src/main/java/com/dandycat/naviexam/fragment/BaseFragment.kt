package com.dandycat.naviexam.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * 베이스 프래그먼트
 * 해당 화면서는 BasePrimaryFragment, BaseChildFragment에 상속을 주도록 한다.
 */
abstract class BaseFragment<V : ViewDataBinding>(private val layoutResId : Int) : Fragment() {

    abstract fun init()

    lateinit var binding: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,layoutResId,container,false)
        init()
        return binding.root
    }
}