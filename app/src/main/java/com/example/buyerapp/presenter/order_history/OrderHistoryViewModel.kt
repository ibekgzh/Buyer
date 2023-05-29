package com.example.buyerapp.presenter.order_history

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.GetOrdersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderHistoryViewModel @Inject constructor(val getOrdersUseCase: GetOrdersUseCase) :
    MviViewModel<OrderHistoryViewState, OrderHistoryEvent, OrderHistoryEffect>() {
    override fun onTriggerEvent(eventType: OrderHistoryEvent) {
        when (eventType) {
            is OrderHistoryEvent.LoadOrders -> onLoadOrders(
                eventType.startDate,
                eventType.endDate,
                eventType.pageNum,
                eventType.pageSize
            )
        }
    }

    private fun onLoadOrders(startDate: String, endDate: String, pageNum: Int, pageSize: Int) =
        safeLaunch {
            execute(
                getOrdersUseCase(
                    GetOrdersUseCase.Params(
                        startDate,
                        endDate,
                        pageNum,
                        pageSize
                    )
                )
            ) {
                setState(OrderHistoryViewState(orders = it))
            }
        }
}