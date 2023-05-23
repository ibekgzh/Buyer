package com.example.buyerapp.domain.di

import com.example.buyerapp.domain.repository.AuthBuyerRepository
import com.example.buyerapp.domain.repository.OnBoardingRepository
import com.example.buyerapp.domain.repository.UserRepository
import com.example.buyerapp.domain.usecase.AuthCompleteUseCase
import com.example.buyerapp.domain.usecase.AuthStartUseCase
import com.example.buyerapp.domain.usecase.GetOnBoardingItemsUseCase
import com.example.buyerapp.domain.usecase.GetUserInfoUseCase
import com.example.buyerapp.domain.usecase.SaveUserInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetOnBoardingItems(onBoardingRepository: OnBoardingRepository): GetOnBoardingItemsUseCase =
        GetOnBoardingItemsUseCase(onBoardingRepository)

    @Singleton
    @Provides
    fun provideAuthStart(authBuyerRepository: AuthBuyerRepository): AuthStartUseCase =
        AuthStartUseCase(authBuyerRepository)

    @Singleton
    @Provides
    fun provideAuthComplete(authBuyerRepository: AuthBuyerRepository): AuthCompleteUseCase =
        AuthCompleteUseCase(authBuyerRepository)

    @Singleton
    @Provides
    fun provideGetUserInfoUseCase(userRepository: UserRepository): GetUserInfoUseCase =
        GetUserInfoUseCase(userRepository)

    @Singleton
    @Provides
    fun provideSaveUserInfoUseCase(userRepository: UserRepository): SaveUserInfoUseCase =
        SaveUserInfoUseCase(userRepository)

}