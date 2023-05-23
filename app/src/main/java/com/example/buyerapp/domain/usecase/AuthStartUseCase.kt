package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.repository.AuthBuyerRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class AuthStartUseCase @Inject constructor(private val repository: AuthBuyerRepository) :
    DataStateUseCase<AuthStartUseCase.Params, Unit>() {

    data class Params(
        val cellPhone: String
    )

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = apiCall {
            repository.onSendPhoneToReceiveOtp(params.cellPhone)
        }

        emit(result)
    }

}