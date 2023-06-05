package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.Store
import com.example.buyerapp.domain.repository.StoreRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetSearchStoreUseCase @Inject constructor(private val storeRepository: StoreRepository):
    DataStateUseCase<GetSearchStoreUseCase.Params, Pageable<Store>>() {
        data class Params (
            val searchWord: String,
            val pageNum: Int,
            val pageSize: Int
        )

    override suspend fun FlowCollector<DataState<Pageable<Store>>>.execute(params: Params) {
        val result = apiCall {
            storeRepository.search(params.searchWord, params.pageNum, params.pageSize)
        }
        emit(result)
    }
}