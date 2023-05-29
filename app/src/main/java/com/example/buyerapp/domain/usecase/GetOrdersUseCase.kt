package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.order_info.Order
import com.example.buyerapp.domain.repository.OrderRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(val orderRepository: OrderRepository) :
    DataStateUseCase<GetOrdersUseCase.Params, List<Order>>() {

    data class Params(
        val startDate: String,
        val endDate: String,
        val pageNum: Int,
        val pageSize: Int
    )

    override suspend fun FlowCollector<DataState<List<Order>>>.execute(params: Params) {
        val result = apiCall {
            orderRepository.getOrders(
                params.startDate,
                params.endDate,
                params.pageNum,
                params.pageSize
            )
        }
        emit(result)
    }

}