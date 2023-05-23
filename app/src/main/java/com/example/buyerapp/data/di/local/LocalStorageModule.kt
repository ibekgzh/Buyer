package com.example.buyerapp.data.di.local

import android.content.Context
import com.example.buyerapp.data.local.GlobalKeyValueCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {

    @Singleton
    @Provides
    fun provideGlobalKeyValueCache(@ApplicationContext context: Context): GlobalKeyValueCache =
        GlobalKeyValueCache(context)
}