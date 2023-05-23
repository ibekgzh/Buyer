package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : DataStateUseCase<AddProductUseCase.Params, Unit>() {

    data class Params(
        val amount: String,
        val itemId: String
    )

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = apiCall {
            productRepository.addProduct(params.amount, params.itemId)
        }
        emit(result)
    }

}