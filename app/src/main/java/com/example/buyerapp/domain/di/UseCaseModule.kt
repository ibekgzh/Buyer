package com.example.buyerapp.domain.di

import com.example.buyerapp.domain.repository.AuthBuyerRepository
import com.example.buyerapp.domain.repository.CartRepository
import com.example.buyerapp.domain.repository.GlobalKeyValueRepository
import com.example.buyerapp.domain.repository.OnBoardingRepository
import com.example.buyerapp.domain.repository.OrderRepository
import com.example.buyerapp.domain.repository.ProductRepository
import com.example.buyerapp.domain.repository.PromoRepository
import com.example.buyerapp.domain.repository.StoreRepository
import com.example.buyerapp.domain.repository.UserRepository
import com.example.buyerapp.domain.usecase.AddProductUseCase
import com.example.buyerapp.domain.usecase.AuthCompleteUseCase
import com.example.buyerapp.domain.usecase.AuthStartUseCase
import com.example.buyerapp.domain.usecase.ClearAuthKeyUseCase
import com.example.buyerapp.domain.usecase.DeleteCachedStoreUseCase
import com.example.buyerapp.domain.usecase.DeleteCartItemUseCase
import com.example.buyerapp.domain.usecase.GetAuthKeyUseCase
import com.example.buyerapp.domain.usecase.GetCartItemsUseCase
import com.example.buyerapp.domain.usecase.GetOnBoardingItemsUseCase
import com.example.buyerapp.domain.usecase.GetOrderDetailsUseCase
import com.example.buyerapp.domain.usecase.GetOrdersUseCase
import com.example.buyerapp.domain.usecase.GetProductByBarCodeUseCase
import com.example.buyerapp.domain.usecase.GetPromotionsUseCase
import com.example.buyerapp.domain.usecase.GetSearchStoreUseCase
import com.example.buyerapp.domain.usecase.GetCachedStoreUseCase
import com.example.buyerapp.domain.usecase.GetStoreDetailsUseCase
import com.example.buyerapp.domain.usecase.GetUserInfoUseCase
import com.example.buyerapp.domain.usecase.ModifyCartItemUseCase
import com.example.buyerapp.domain.usecase.OrderUseCase
import com.example.buyerapp.domain.usecase.PinChangeUseCase
import com.example.buyerapp.domain.usecase.PinResetConfirmUseCase
import com.example.buyerapp.domain.usecase.PinResetSmsUseCase
import com.example.buyerapp.domain.usecase.SaveAuthKeyUseCase
import com.example.buyerapp.domain.usecase.SaveStoreUseCase
import com.example.buyerapp.domain.usecase.SaveUserInfoUseCase
import com.example.buyerapp.domain.usecase.StoreNotifyStateUseCase
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
    fun provideGetAuthKeyUseCase(repository: GlobalKeyValueRepository): GetAuthKeyUseCase =
        GetAuthKeyUseCase(repository)

    @Singleton
    @Provides
    fun provideSaveAuthKeyUseCase(repository: GlobalKeyValueRepository): SaveAuthKeyUseCase =
        SaveAuthKeyUseCase(repository)

    @Singleton
    @Provides
    fun provideClearAuthKeyUseCase(repository: GlobalKeyValueRepository): ClearAuthKeyUseCase =
        ClearAuthKeyUseCase(repository)

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

    @Singleton
    @Provides
    fun provideGetProductByBarCodeUseCase(productRepository: ProductRepository): GetProductByBarCodeUseCase =
        GetProductByBarCodeUseCase(productRepository)

    @Singleton
    @Provides
    fun provideAddProductUseCase(productRepository: ProductRepository): AddProductUseCase =
        AddProductUseCase(productRepository)

    @Singleton
    @Provides
    fun providePinResetSmsUseCase(authBuyerRepository: AuthBuyerRepository): PinResetSmsUseCase =
        PinResetSmsUseCase(authBuyerRepository)

    @Singleton
    @Provides
    fun providePinResetConfirmUseCase(authBuyerRepository: AuthBuyerRepository): PinResetConfirmUseCase =
        PinResetConfirmUseCase(authBuyerRepository)

    @Singleton
    @Provides
    fun providePinChangeUseCase(authBuyerRepository: AuthBuyerRepository): PinChangeUseCase =
        PinChangeUseCase(authBuyerRepository)

    @Singleton
    @Provides
    fun provideGetOrdersUseCase(orderRepository: OrderRepository): GetOrdersUseCase =
        GetOrdersUseCase(orderRepository)

    @Singleton
    @Provides
    fun provideGetCartItemsUseCase(cartRepository: CartRepository): GetCartItemsUseCase =
        GetCartItemsUseCase(cartRepository)

    @Singleton
    @Provides
    fun provideModifyCartItemUseCase(cartRepository: CartRepository): ModifyCartItemUseCase =
        ModifyCartItemUseCase(cartRepository)

    @Singleton
    @Provides
    fun provideDeleteCartItemUseCase(cartRepository: CartRepository): DeleteCartItemUseCase =
        DeleteCartItemUseCase(cartRepository)

    @Singleton
    @Provides
    fun provideOrderUseCase(orderRepository: OrderRepository): OrderUseCase =
        OrderUseCase(orderRepository)

    @Singleton
    @Provides
    fun provideGetOrderDetailsUseCase(orderRepository: OrderRepository): GetOrderDetailsUseCase =
        GetOrderDetailsUseCase(orderRepository)

    @Singleton
    @Provides
    fun provideGetPromotionsUseCase(promoRepository: PromoRepository): GetPromotionsUseCase =
        GetPromotionsUseCase(promoRepository)

    @Singleton
    @Provides
    fun provideGetStoreIdUseCase(storeRepository: StoreRepository): GetCachedStoreUseCase =
        GetCachedStoreUseCase(storeRepository)

    @Singleton
    @Provides
    fun provideGetSearchStoreUseCase(storeRepository: StoreRepository): GetSearchStoreUseCase =
        GetSearchStoreUseCase(storeRepository)

    @Singleton
    @Provides
    fun provideSaveStoreUseCase(storeRepository: StoreRepository): SaveStoreUseCase =
        SaveStoreUseCase(storeRepository)

    @Singleton
    @Provides
    fun provideDeleteCachedStoreUseCase(storeRepository: StoreRepository): DeleteCachedStoreUseCase =
        DeleteCachedStoreUseCase(storeRepository)

    @Singleton
    @Provides
    fun provideGetStoreDetails(storeRepository: StoreRepository): GetStoreDetailsUseCase =
        GetStoreDetailsUseCase(storeRepository)

    @Singleton
    @Provides
    fun provideStoreNotifyStateUseCase(storeRepository: StoreRepository): StoreNotifyStateUseCase =
        StoreNotifyStateUseCase(storeRepository)
}