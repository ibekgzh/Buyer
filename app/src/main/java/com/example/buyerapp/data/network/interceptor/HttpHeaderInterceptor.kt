package com.example.buyerapp.data.network.interceptor

import com.example.buyerapp.data.local.GlobalKeyValueCache
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HttpHeaderInterceptor  @Inject constructor(private val globalKeyValueCache: GlobalKeyValueCache) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val key = runBlocking {
            val key = globalKeyValueCache.getAuthKey();
            return@runBlocking key.first()
        }

        val original = chain.request();

        key?.let {
            val request = original.newBuilder()
                .header("Authorization", key)
                .method(original.method, original.body)
                .build()

            return chain.proceed(request)
        }

        return chain.proceed(original)
    }
}