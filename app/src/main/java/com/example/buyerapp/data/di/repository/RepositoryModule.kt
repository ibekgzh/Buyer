package com.example.buyerapp.data.di.repository

import com.example.buyerapp.data.local.GlobalKeyValueCache
import com.example.buyerapp.data.local.dao.StoreDao
import com.example.buyerapp.data.local.dao.UserDao
import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.repository.AuthBuyerRepositoryImpl
import com.example.buyerapp.data.repository.CartRepositoryImpl
import com.example.buyerapp.data.repository.GlobalKeyValueRepositoryImpl
import com.example.buyerapp.data.repository.NotificationRepositoryImpl
import com.example.buyerapp.data.repository.OnBoardingRepositoryImpl
import com.example.buyerapp.data.repository.OrderRepositoryImpl
import com.example.buyerapp.data.repository.ProductRepositoryImpl
import com.example.buyerapp.data.repository.PromoRepositoryImpl
import com.example.buyerapp.data.repository.StoreRepositoryImpl
import com.example.buyerapp.data.repository.UserRepositoryImpl
import com.example.buyerapp.domain.repository.AuthBuyerRepository
import com.example.buyerapp.domain.repository.CartRepository
import com.example.buyerapp.domain.repository.GlobalKeyValueRepository
import com.example.buyerapp.domain.repository.NotificationRepository
import com.example.buyerapp.domain.repository.OnBoardingRepository
import com.example.buyerapp.domain.repository.OrderRepository
import com.example.buyerapp.domain.repository.ProductRepository
import com.example.buyerapp.domain.repository.PromoRepository
import com.example.buyerapp.domain.repository.StoreRepository
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
    fun provideUserRepository(apiService: ApiService, userDao: UserDao): UserRepository =
        UserRepositoryImpl(apiService, userDao)

    @Singleton
    @Provides
    fun provideProductRepository(apiService: ApiService): ProductRepository =
        ProductRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideOrderRepository(apiService: ApiService): OrderRepository =
        OrderRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideCartRepository(apiService: ApiService): CartRepository =
        CartRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun providePromoRepository(apiService: ApiService): PromoRepository =
        PromoRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideStoreRepository(apiService: ApiService, storeDao: StoreDao): StoreRepository =
        StoreRepositoryImpl(apiService, storeDao)

    @Singleton
    @Provides
    fun provideNotificationRepository(apiService: ApiService): NotificationRepository =
        NotificationRepositoryImpl(apiService)
}