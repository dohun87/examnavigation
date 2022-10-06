package com.dandycat.naviexam.fragment

import androidx.activity.addCallback
import androidx.databinding.ViewDataBinding

/**
 * 주 화면(홈,프로필등) 내비게이션이 직접 연동되는 Fragment에 대한 BaseFragment
 * 해당 프래그먼트를 상속받는 경우에는 백키 동작을 적용 시켜줄 수 있게 한다.
 * @author dohun8832
 */
abstract class BasePrimaryFragment<V : ViewDataBinding>(layoutResId : Int) : BaseFragment<V>(layoutResId) {

    abstract fun initWidget()
    abstract fun onBackPressed()
    override fun init() {
        activity?.onBackPressedDispatcher?.addCallback {
            onBackPressed()
        }
        initWidget()
    }

}