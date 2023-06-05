package com.example.buyerapp.domain.model.order_info

import com.example.buyerapp.domain.model.MeasureUnit


data class OrderDetails (
    val id: Long,
    val items: List<OrderDetailsItem>,
    val paymentType: PaymentType,
    val price: Int,
    val qrCode: String,
    val regDate: String,
    val state: State,
    val store: Store
)

data class OrderDetailsItem (
    val item: OrderItem,
    val price: Int,
    val quantity: Int,
    val totalPrice: Int
)

data class OrderItem(
    val id: Long,
    val measureUnit: MeasureUnit,
    val title: String
)

data class PaymentType(
    val descr: String,
    val stateId: Int
)




