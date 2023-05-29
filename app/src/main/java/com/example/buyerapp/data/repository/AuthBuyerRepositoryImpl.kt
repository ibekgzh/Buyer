package com.example.buyerapp.data.repository

import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.auth.AuthCompleteReq
import com.example.buyerapp.data.network.dto.auth.AuthStartReq
import com.example.buyerapp.data.network.dto.auth.PinChangeReq
import com.example.buyerapp.data.network.dto.auth.PinCheckReq
import com.example.buyerapp.data.network.dto.auth.PinResetConfirmReq
import com.example.buyerapp.data.network.dto.auth.toDomain
import com.example.buyerapp.domain.model.AuthComplete
import com.example.buyerapp.domain.repository.AuthBuyerRepository
import javax.inject.Inject

class AuthBuyerRepositoryImpl @Inject constructor(val apiService: ApiService) :
    AuthBuyerRepository {

    override suspend fun onSendPhoneToReceiveOtp(cellPhone: String) {
        return apiService.sendPhoneToReceiveOtp(AuthStartReq(cellPhone))
    }

    override suspend fun onComplete(
        smsToken: String,
        cellPhone: String,
        newPin: String
    ): AuthComplete {
        return apiService.onComplete(smsToken, AuthCompleteReq(cellPhone, newPin)).toDomain()
    }

    override suspend fun onLogout() {
        return apiService.authLogout()
    }

    override suspend fun pinCheck(pin: String) {
        return apiService.authPinCheck(PinCheckReq(pin))
    }

    override suspend fun pinChange(oldPin: String, newPin: String) {
        return apiService.authPinChange(PinChangeReq(oldPin, newPin))
    }

    override suspend fun pinResetSms() {
        return apiService.authPinResetSms()
    }

    override suspend fun pinResetConfirm(pinOtp: String, pin: String) {
        return apiService.authPinResetConfirm(pinOtp, PinResetConfirmReq(pin))
    }
}