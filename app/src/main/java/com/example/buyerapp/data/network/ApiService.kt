package com.example.buyerapp.data.network

import com.example.buyerapp.data.network.dto.BasketCartItemRes
import com.example.buyerapp.data.network.dto.OnBoardingItemRes
import com.example.buyerapp.data.network.dto.Product
import com.example.buyerapp.data.network.dto.ProductInfoRes
import com.example.buyerapp.data.network.dto.auth.AuthCompleteReq
import com.example.buyerapp.data.network.dto.auth.AuthCompleteRes
import com.example.buyerapp.data.network.dto.auth.AuthStartReq
import com.example.buyerapp.data.network.dto.auth.PinChangeReq
import com.example.buyerapp.data.network.dto.auth.PinCheckReq
import com.example.buyerapp.data.network.dto.auth.PinResetConfirmReq
import com.example.buyerapp.data.network.dto.order_info.OrderRes
import com.example.buyerapp.data.network.dto.user_info.UserInfoReq
import com.example.buyerapp.data.network.dto.user_info.UserInfoRes
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
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
    suspend fun getBasketCartItems(): BasketCartItemRes

    @POST("order/page")
    suspend fun getOrders(
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): List<OrderRes>

}