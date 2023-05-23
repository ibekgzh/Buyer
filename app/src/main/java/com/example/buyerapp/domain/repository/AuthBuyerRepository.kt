package com.example.buyerapp.domain.repository

import com.example.buyerapp.domain.model.AuthComplete

interface AuthBuyerRepository {

    suspend fun onSendPhoneToReceiveOtp(cellPhone: String)

    suspend fun onComplete(smsToken: String, cellPhone: String, newPin: String): AuthComplete

    suspend fun pinCheck(pin: String)

    suspend fun onLogout()
}