package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.ProductInfo
import com.example.buyerapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetProductByBarCodeUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : DataStateUseCase<GetProductByBarCodeUseCase.Params, ProductInfo>() {

    data class Params(
        val barcode: String,
        val storeId: Long,
        val uid: String? = null
    )

    override suspend fun FlowCollector<DataState<ProductInfo>>.execute(params: Params) {

        val result = apiCall {
            productRepository.getProductInfo(params.barcode, params.storeId, params.uid)
        }
        emit(result)
    }
}