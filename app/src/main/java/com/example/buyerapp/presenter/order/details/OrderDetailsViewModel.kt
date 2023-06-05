package com.example.buyerapp.presenter.order.details

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.GetOrderDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(private val getOrderDetailsUseCase: GetOrderDetailsUseCase):
    MviViewModel<OrderDetailsViewState, OrderDetailsEvent, OrderDetailsEffect>(){
    override fun onTriggerEvent(eventType: OrderDetailsEvent) {
        when(eventType) {
            is OrderDetailsEvent.LoadDetails -> onLoadDetails(eventType.id)
        }
    }

    private fun onLoadDetails(id: Long) = safeLaunch {
        execute(getOrderDetailsUseCase(id)) {
            setState(OrderDetailsViewState(orderDetails = it))
        }
    }
}