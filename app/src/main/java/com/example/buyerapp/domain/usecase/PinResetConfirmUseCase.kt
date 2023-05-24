package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.repository.AuthBuyerRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class PinResetConfirmUseCase @Inject constructor(val authBuyerRepository: AuthBuyerRepository)
    : DataStateUseCase<PinResetConfirmUseCase.Params, Unit>() {
        data class Params(
            val pinOtp: String,
            val pin: String
        )

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = apiCall {
            authBuyerRepository.pinResetConfirm(params.pinOtp, params.pin)
        }
        emit(result)
    }
}