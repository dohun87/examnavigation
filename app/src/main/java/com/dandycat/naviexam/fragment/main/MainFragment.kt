package com.dandycat.naviexam.fragment.main

import androidx.fragment.app.Fragment
import com.dandycat.naviexam.R
import com.dandycat.naviexam.adapter.MainFragmentAdapter
import com.dandycat.naviexam.databinding.FragmentMainBinding
import com.dandycat.naviexam.fragment.BasePrimaryFragment
import com.dandycat.naviexam.util.Logger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment() : BasePrimaryFragment<FragmentMainBinding>(R.layout.fragment_main) {

    @Inject lateinit var mAdapter : MainFragmentAdapter

    private fun createMainSubFragmentList() : MutableList<Fragment> = mutableListOf<Fragment>().apply {
        add(MainHomeFragment())
        add(MainProfileFragment())
    }

    override fun initWidget() {
        binding.pager.isUserInputEnabled = false
        mAdapter.setFragmentList(createMainSubFragmentList())
        binding.pager.adapter = mAdapter
    }

    override fun backPressed() {
        requireActivity().finish()
    }
}