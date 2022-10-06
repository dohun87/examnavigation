package com.dandycat.data.di

import android.content.Context
import com.dandycat.data.pref.PreferenceModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideDi {

    @Provides
    @Singleton
    fun providePreferenceModule(@ApplicationContext context : Context) = PreferenceModule(context)

}