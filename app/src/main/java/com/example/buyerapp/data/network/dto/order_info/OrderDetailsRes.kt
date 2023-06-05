package com.example.buyerapp.data.network.dto.order_info

import com.example.buyerapp.data.network.dto.MeasureUnit
import com.example.buyerapp.data.network.dto.toDomain
import com.example.buyerapp.domain.model.order_info.OrderDetails
import com.example.buyerapp.domain.model.order_info.OrderDetailsItem
import com.example.buyerapp.domain.model.order_info.OrderItem
import com.example.buyerapp.domain.model.order_info.PaymentType
import com.example.buyerapp.domain.model.order_info.State

data class OrderDetailsRes (
    val id: Long,
    val items: List<OrderDetailsItemRes>,
    val paymentType: PaymentTypeRes,
    val price: Int,
    val qrCode: String,
    val regDate: String,
    val state: StateOrderRes,
    val store: StoreRes
)

fun OrderDetailsRes.toDomain() =
    OrderDetails(
        id,
        items.toDomain(),
        paymentType.toDomain(),
        price,
        qrCode,
        regDate,
        state.toDomain(),
        store.toDomain()
    )

data class OrderDetailsItemRes (
    val item: OrderItemRes,
    val price: Int,
    val quantity: Int,
    val totalPrice: Int
)

fun OrderDetailsItemRes.toDomain() =
    OrderDetailsItem(
        item.toDomain(),
        price,
        quantity,
        totalPrice
    )

fun List<OrderDetailsItemRes>.toDomain() = map { it.toDomain() }

data class OrderItemRes(
    val id: Long,
    val measureUnit: MeasureUnit,
    val title: String
)

fun OrderItemRes.toDomain() = OrderItem(id, measureUnit.toDomain(), title)

data class PaymentTypeRes(
    val descr: String,
    val stateId: Int
)

fun PaymentTypeRes.toDomain() = PaymentType(descr, stateId)

data class StateOrderRes(
    val color: String,
    val descr: String,
    val stateId: Long
)

fun StateOrderRes.toDomain() = State(stateId, descr, color)


