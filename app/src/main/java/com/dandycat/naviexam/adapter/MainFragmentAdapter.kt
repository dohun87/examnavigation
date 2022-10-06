package com.dandycat.naviexam.adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import javax.inject.Inject

class MainFragmentAdapter @Inject constructor(context : Context) : FragmentStateAdapter(context as AppCompatActivity){

    private val mFragmentList = mutableListOf<Fragment>()

    fun setFragmentList(fragList : MutableList<Fragment>){
        mFragmentList.addAll(fragList)

    }

    override fun getItemCount() = mFragmentList.size

    override fun createFragment(position: Int): Fragment = mFragmentList[position]
}