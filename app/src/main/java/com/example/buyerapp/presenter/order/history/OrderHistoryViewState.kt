package com.example.buyerapp.presenter.order.history

import com.example.buyerapp.core.constants.StandardPageSize
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.util.dateBefore1Year
import com.example.buyerapp.core.util.dateTomorrow
import com.example.buyerapp.domain.model.order_info.Order

data class OrderHistoryViewState(
    val orders: List<Order>
)

sealed class OrderHistoryEvent {
    data class LoadOrders(
        val startDate: String = dateBefore1Year(),
        val endDate: String = dateTomorrow(),
        val pageNum: Int = 0,
        val pageSize: Int = StandardPageSize
    ): OrderHistoryEvent()
}

sealed class OrderHistoryEffect : BaseEffect() {

}
