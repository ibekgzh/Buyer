package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.repository.AuthBuyerRepository
import com.example.buyerapp.domain.repository.GlobalKeyValueRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authBuyerRepository: AuthBuyerRepository,
    private val globalKeyValueRepository: GlobalKeyValueRepository
) :
    DataStateUseCase<Unit, Unit>() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Unit) {
        val result = apiCall {
            authBuyerRepository.onLogout()
        }
        globalKeyValueRepository.clearAuthKey()
        emit(result)
    }
}