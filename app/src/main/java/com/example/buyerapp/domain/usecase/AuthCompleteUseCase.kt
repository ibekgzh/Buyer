package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.AuthComplete
import com.example.buyerapp.domain.repository.AuthBuyerRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class AuthCompleteUseCase @Inject constructor(private val authBuyerRepository: AuthBuyerRepository) :
    DataStateUseCase<AuthCompleteUseCase.Params, AuthComplete>() {

    data class Params(
        val smsToken: String,
        val cellphone: String,
        val pin: String
    )

    override suspend fun FlowCollector<DataState<AuthComplete>>.execute(params: Params) {
        val result = apiCall {
            authBuyerRepository.onComplete(params.smsToken, params.cellphone, params.pin);
        }
        emit(result)
    }

}