package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.Cart
import com.example.buyerapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(private val cartRepository: CartRepository):
    DataStateUseCase<Unit, Cart>() {

    override suspend fun FlowCollector<DataState<Cart>>.execute(params: Unit) {
        val result = apiCall {
            cartRepository.getCart()
        }
        emit(result)
    }

}