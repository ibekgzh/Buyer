package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.promo.Promo
import com.example.buyerapp.domain.repository.PromoRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetPromotionsUseCase @Inject constructor(private val promoRepository: PromoRepository):
DataStateUseCase<GetPromotionsUseCase.Params, Pageable<Promo>>(){
    data class Params(
        val storeId: Long,
        val page: Int,
        val pageSize: Int
    )

    override suspend fun FlowCollector<DataState<Pageable<Promo>>>.execute(params: Params) {
        val result = apiCall {
            promoRepository.getPromotions(params.storeId, params.page, params.pageSize)
        }
        emit(result)
    }
}