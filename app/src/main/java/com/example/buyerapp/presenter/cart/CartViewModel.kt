package com.example.buyerapp.presenter.cart

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.model.Cart
import com.example.buyerapp.domain.usecase.DeleteCartItemUseCase
import com.example.buyerapp.domain.usecase.GetCartItemsUseCase
import com.example.buyerapp.domain.usecase.ModifyCartItemUseCase
import com.example.buyerapp.domain.usecase.OrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val modifyCartItemUseCase: ModifyCartItemUseCase,
    private val deleteCartItemUseCase: DeleteCartItemUseCase,
    private val orderUseCase: OrderUseCase
): MviViewModel<CartViewState, CartEvent, CartEffect>() {

    override fun onTriggerEvent(eventType: CartEvent) {
        when(eventType){
            is CartEvent.LoadCartItems -> onLoadCartItems()
            is CartEvent.Modify -> onModify(eventType.amount, eventType.itemId)
            is CartEvent.Delete -> onDelete(eventType.amount, eventType.itemId)
            is CartEvent.Order -> onOrder(eventType.cart)
        }
    }

    private fun onLoadCartItems() = safeLaunch {
        execute(getCartItemsUseCase(Unit)) {
            setState(CartViewState(cart = it))
        }
    }

    private fun onModify(amount: Int, itemId: Int) = safeLaunch {
        execute(modifyCartItemUseCase(ModifyCartItemUseCase.Params(amount, itemId))) {
            setState(CartViewState(cart = it))
        }
    }

    private fun onDelete(amount: Int, itemId: Int) = safeLaunch {
        execute(deleteCartItemUseCase(DeleteCartItemUseCase.Params(amount, itemId))) {
            setState(CartViewState(cart = it))
        }
    }

    private fun onOrder(cart: Cart) = safeLaunch {
        execute(orderUseCase(cart)){
            effectChannel.trySend(CartEffect.Ordered(it))
        }
    }

}