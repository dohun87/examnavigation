package com.dandycat.naviexam.di

import android.content.Context
import com.dandycat.naviexam.util.DynamicLinkUtil
import com.dandycat.naviexam.util.ToastModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingleTonDi {

    @Provides
    @Singleton
    fun provideToastModule(@ApplicationContext context : Context) = ToastModule(context)

    @Provides
    @Singleton
    fun provideDynamicLinkUtil(@ApplicationContext context : Context) = DynamicLinkUtil(context)
}