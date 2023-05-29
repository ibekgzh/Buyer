package com.example.buyerapp.presenter.order_history

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.util.dateTomorrow
import com.example.buyerapp.core.util.dateYesterday
import com.example.buyerapp.domain.model.order_info.Order

data class OrderHistoryViewState(
    val orders: List<Order>
)

sealed class OrderHistoryEvent {
    data class LoadOrders(
        val startDate: String = dateYesterday(),
        val endDate: String = dateTomorrow(),
        val pageNum: Int = 0,
        val pageSize: Int = StandardPageSize
    ): OrderHistoryEvent()
}

sealed class OrderHistoryEffect : BaseEffect() {

}

const val StandardPageSize = 20