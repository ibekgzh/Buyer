package com.example.buyerapp.data.di.repository

import com.example.buyerapp.data.local.GlobalKeyValueCache
import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.repository.AuthBuyerRepositoryImpl
import com.example.buyerapp.data.repository.GlobalKeyValueRepositoryImpl
import com.example.buyerapp.data.repository.OnBoardingRepositoryImpl
import com.example.buyerapp.data.repository.UserRepositoryImpl
import com.example.buyerapp.domain.repository.AuthBuyerRepository
import com.example.buyerapp.domain.repository.GlobalKeyValueRepository
import com.example.buyerapp.domain.repository.OnBoardingRepository
import com.example.buyerapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideOnBoardingRepository(apiService: ApiService): OnBoardingRepository =
        OnBoardingRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideAuthorizationRepository(apiService: ApiService): AuthBuyerRepository =
        AuthBuyerRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideGlobalKeyValueRepository(globalKeyValueCache: GlobalKeyValueCache): GlobalKeyValueRepository =
        GlobalKeyValueRepositoryImpl(globalKeyValueCache)

    @Singleton
    @Provides
    fun provideUserRepository(apiService: ApiService): UserRepository =
        UserRepositoryImpl(apiService)

}