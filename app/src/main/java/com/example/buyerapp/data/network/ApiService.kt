package com.example.buyerapp.data.network

import com.example.buyerapp.data.network.dto.CartItemDeleteReq
import com.example.buyerapp.data.network.dto.CartItemModifyReq
import com.example.buyerapp.data.network.dto.CartRes
import com.example.buyerapp.data.network.dto.OnBoardingItemRes
import com.example.buyerapp.data.network.dto.PageableContent
import com.example.buyerapp.data.network.dto.Product
import com.example.buyerapp.data.network.dto.ProductInfoRes
import com.example.buyerapp.data.network.dto.StoreNotifyStateReq
import com.example.buyerapp.data.network.dto.auth.AuthCompleteReq
import com.example.buyerapp.data.network.dto.auth.AuthCompleteRes
import com.example.buyerapp.data.network.dto.auth.AuthStartReq
import com.example.buyerapp.data.network.dto.auth.PinChangeReq
import com.example.buyerapp.data.network.dto.auth.PinCheckReq
import com.example.buyerapp.data.network.dto.auth.PinResetConfirmReq
import com.example.buyerapp.data.network.dto.fcm.LastNotificationOfStoreRes
import com.example.buyerapp.data.network.dto.order_info.OrderCreateReq
import com.example.buyerapp.data.network.dto.order_info.OrderCreateRes
import com.example.buyerapp.data.network.dto.order_info.OrderDetailsRes
import com.example.buyerapp.data.network.dto.order_info.OrderRes
import com.example.buyerapp.data.network.dto.promo.PromoRes
import com.example.buyerapp.data.network.dto.store.StoreDetailsRes
import com.example.buyerapp.data.network.dto.store.StoreRes
import com.example.buyerapp.data.network.dto.user_info.UserInfoReq
import com.example.buyerapp.data.network.dto.user_info.UserInfoRes
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("info/onboarding")
    suspend fun getOnBoarding(): List<OnBoardingItemRes>

    @POST("auth/start")
    suspend fun sendPhoneToReceiveOtp(@Body authStartReq: AuthStartReq)

    @POST("auth/complete")
    suspend fun onComplete(
        @Header("smsToken") smsToken: String,
        @Body authComplete: AuthCompleteReq
    ): AuthCompleteRes

    @POST("auth/pin/check")
    suspend fun authPinCheck(@Body pinCheckReq: PinCheckReq)

    @POST("auth/pin/change")
    suspend fun authPinChange(@Body pinChangeReq: PinChangeReq)

    @DELETE("auth/logout")
    suspend fun authLogout()

    @POST("auth/pin/reset/sms")
    suspend fun authPinResetSms()

    @POST("auth/pin/reset/confirm")
    suspend fun authPinResetConfirm(
        @Header("pinOTP") pinOtp: String,
        @Body pinResetConfirmReq: PinResetConfirmReq
    )

    @GET("user")
    suspend fun getUserInfo(): UserInfoRes

    @PUT("user")
    suspend fun updateUserInfo(@Body userInfoReq: UserInfoReq): UserInfoRes

    @GET("store/item")
    suspend fun getProductInfo(
        @Query("barcode") barcode: String,
        @Query("storeId") storeId: Long,
        @Query("uid") uid: String?
    ): ProductInfoRes

    @POST("cart/item")
    suspend fun addProduct(@Body product: Product)

    @GET("cart")
    suspend fun getCart(): CartRes

    @PUT("cart/item")
    suspend fun modifyCartItem(@Body cartItemModifyReq: CartItemModifyReq)

    @HTTP(method = "DELETE", path = "cart/item", hasBody = true)
    suspend fun deleteCartItem(@Body cartItemDeleteReq: CartItemDeleteReq)

    @GET("order/page")
    suspend fun getOrders(
        @Query("endDate") endDate: String,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int,
        @Query("startDate") startDate: String
    ): PageableContent<OrderRes>

    @POST("order")
    suspend fun order(@Body orderCreateReq: OrderCreateReq): OrderCreateRes

    @GET("order/{id}")
    suspend fun orderDetails(@Path("id") id: Long): OrderDetailsRes

    @GET("store/{id}")
    suspend fun getStoreDetails(@Path("id") id: Long): StoreDetailsRes

    @GET("promotion")
    suspend fun getPromotion(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int,
        @Query("storeId") storeId: Long
    ): PageableContent<PromoRes>

    @GET("store/search")
    suspend fun search(
        @Query("searchword") searchWord: String,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): PageableContent<StoreRes>

    @POST("store/notification/state")
    suspend fun storeNotifyState(@Body storeNotifyStateReq: StoreNotifyStateReq)

    @GET("/fcm/notification/stores/last")
    suspend fun getLastNotificationsOfStores(): List<LastNotificationOfStoreRes>

    @GET("/fcm/notification/filter")
    suspend fun getNotificationFilter(
        @Query("storeId") storeId: Long,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): PageableContent<LastNotificationOfStoreRes>
}