package com.example.buyerapp.presenter.product_info

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.data.network.dto.Product
import com.example.buyerapp.domain.model.MeasureUnit
import com.example.buyerapp.domain.model.ProductInfo
import com.example.buyerapp.domain.usecase.AddProductUseCase
import com.example.buyerapp.domain.usecase.GetProductByBarCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor(
    private val getProductByBarCodeUseCase: GetProductByBarCodeUseCase,
    private val addProductUseCase: AddProductUseCase
) :
    MviViewModel<ProductViewState, ProductEvent, ProductEffect>() {
    override fun onTriggerEvent(eventType: ProductEvent) {
        when (eventType) {
            is ProductEvent.LoadProductInfo -> onLoadProductInfo(eventType.barcode)
            is ProductEvent.AddProduct -> onAddProduct(eventType.amount, eventType.itemId)
        }
    }

    private fun onLoadProductInfo(barcode: String) = safeLaunch {
        execute(getProductByBarCodeUseCase(GetProductByBarCodeUseCase.Params(barcode))) {
            setState(ProductViewState(productInfo = it, product = Product("", "")))
        }
    }

    private fun onAddProduct(amount: String, itemId: String) = safeLaunch {
        execute(addProductUseCase(AddProductUseCase.Params(amount, itemId))) {
            setState(
                ProductViewState(
                    productInfo = ProductInfo(
                        0,
                        "",
                        MeasureUnit("", 0, 0, 0),
                        0,
                        "",
                        ""
                    ), product = Product(amount, itemId)
                )
            )
        }
    }
}