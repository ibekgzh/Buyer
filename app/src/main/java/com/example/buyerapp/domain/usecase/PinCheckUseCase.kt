package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.repository.AuthBuyerRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class PinCheckUseCase @Inject constructor(private val authBuyerRepository: AuthBuyerRepository) :
    DataStateUseCase<String, Unit>() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: String) {
        val result = apiCall {
            authBuyerRepository.pinCheck(params);
        }
        emit(result)
    }

}