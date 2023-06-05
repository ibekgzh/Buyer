package com.example.buyerapp.presenter.order.details

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.order_info.OrderDetails

data class OrderDetailsViewState(
    val orderDetails: OrderDetails
)

sealed class OrderDetailsEvent {
    data class LoadDetails(val id: Long): OrderDetailsEvent()
}

sealed class OrderDetailsEffect : BaseEffect() {

}
