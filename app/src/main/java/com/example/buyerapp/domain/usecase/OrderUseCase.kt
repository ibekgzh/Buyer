package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.Cart
import com.example.buyerapp.domain.repository.OrderRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class OrderUseCase @Inject constructor(private val orderRepository: OrderRepository):
    DataStateUseCase<Cart, Int>() {
    override suspend fun FlowCollector<DataState<Int>>.execute(params: Cart) {
        val result = apiCall {
            orderRepository.order(cart = params)
        }
        emit(result)
    }
}