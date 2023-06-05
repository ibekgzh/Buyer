package com.example.buyerapp.presenter.cart

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.Cart


data class CartViewState(
    var cart: Cart
)

sealed class CartEvent {
    object LoadCartItems : CartEvent()
    data class Modify(val amount: Int, val itemId: Int) : CartEvent()

    data class Delete(val amount: Int, val itemId: Int) : CartEvent()

    data class Order(val cart: Cart) : CartEvent()
}

sealed class CartEffect : BaseEffect() {
    data class Ordered(val id: Int) : CartEffect()
}