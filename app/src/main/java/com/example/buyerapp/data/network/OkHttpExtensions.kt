package com.example.buyerapp.data.network

import com.example.buyerapp.core.framework.network.Failure
import com.example.buyerapp.data.network.dto.ErrorRes
import com.google.gson.Gson
import retrofit2.HttpException

fun HttpException.apiError(): Failure.ApiError? {
    val errorBody = this.response()?.errorBody()?.string()
    return errorBody?.let {
        val errorRes = Gson().fromJson(errorBody, ErrorRes::class.java)
        Failure.ApiError(errorRes.errorCode, errorRes.errorDescr)
    }
}