package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.store.StoreDetails
import com.example.buyerapp.domain.repository.StoreRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class StoreNotifyStateUseCase @Inject constructor(private val storeRepository: StoreRepository):
    DataStateUseCase<StoreNotifyStateUseCase.Params, StoreDetails>() {

    data class Params (
        val active: Boolean,
        val storeId: Long
    )

    override suspend fun FlowCollector<DataState<StoreDetails>>.execute(params: Params) {
        val result = apiCall {
            storeRepository.storeNotifyState(params.active, params.storeId)
            storeRepository.getStoreDetails(params.storeId)
        }
        emit(result)
    }
}