package com.dandycat.naviexam.fragment

import androidx.databinding.ViewDataBinding

/**
 * 차일드 Fragment등에 대한 동작에 대한 BaseFragment
 * 해당 Fragment를 이용하는 화면들은 해당 로직에 연동 시키도록 한다.
 * @author dohun8832
 */
abstract class BaseChildFragment<V : ViewDataBinding>(layoutResId : Int) : BaseFragment<V>(layoutResId){

    abstract fun initWidget()

    override fun init() {
        initWidget()
    }
}