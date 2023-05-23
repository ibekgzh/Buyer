package com.example.buyerapp.data.di.netwok

import android.content.Context
import coil.ImageLoader
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.buyerapp.BuildConfig
import com.example.buyerapp.data.local.GlobalKeyValueCache
import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.interceptor.HttpHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .alwaysReadResponseBody(true)
            .build()
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(globalKeyValueCache: GlobalKeyValueCache): HttpHeaderInterceptor {
        return HttpHeaderInterceptor(globalKeyValueCache)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        httpHeaderInterceptor: HttpHeaderInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpHeaderInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader =
        ImageLoader(context).newBuilder()
            .logger(DebugLogger())
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()

}