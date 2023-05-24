package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.repository.AuthBuyerRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class PinResetSmsUseCase @Inject constructor(val authBuyerRepository: AuthBuyerRepository) :
    DataStateUseCase<Unit, Unit>() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Unit) {
        val result = apiCall {
            authBuyerRepository.pinResetSms()
        }
        emit(result)
    }

}