package com.dandycat.naviexam.di

import android.content.Context
import com.dandycat.naviexam.adapter.MainFragmentAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object ActivityDi {

    @Provides
    fun provideMainFragmentAdapter(@ActivityContext context : Context) = MainFragmentAdapter(context)
}