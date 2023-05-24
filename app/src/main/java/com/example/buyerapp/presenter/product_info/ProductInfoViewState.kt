package com.example.buyerapp.presenter.product_info

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.data.network.dto.Product
import com.example.buyerapp.domain.model.ProductInfo
import com.example.buyerapp.presenter.pincode.PinCodeEffect

data class ProductViewState(
    val productInfo: ProductInfo,
    val product: Product
)

sealed class ProductEvent {
    data class LoadProductInfo(val barcode: String) : ProductEvent()
    data class AddProduct(val amount: String, val itemId: String) : ProductEvent()
}

sealed class ProductEffect : BaseEffect() {
//    TODO OPEN BARCODE
}