package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.order_info.OrderDetails
import com.example.buyerapp.domain.repository.OrderRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetOrderDetailsUseCase @Inject constructor(private val orderRepository: OrderRepository):
    DataStateUseCase<Long, OrderDetails>() {
    override suspend fun FlowCollector<DataState<OrderDetails>>.execute(params: Long) {
        val result = apiCall {
            orderRepository.getOrderDetails(params)
        }
        emit(result)
    }
}