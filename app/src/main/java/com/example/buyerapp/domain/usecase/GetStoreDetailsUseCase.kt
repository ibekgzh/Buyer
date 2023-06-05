package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.store.StoreDetails
import com.example.buyerapp.domain.repository.StoreRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetStoreDetailsUseCase @Inject constructor(private val storeRepository: StoreRepository):
    DataStateUseCase<Int, StoreDetails>() {
    override suspend fun FlowCollector<DataState<StoreDetails>>.execute(params: Int) {
        val result = apiCall {
            storeRepository.getStoreDetails(params)
        }
        emit(result)
    }
}