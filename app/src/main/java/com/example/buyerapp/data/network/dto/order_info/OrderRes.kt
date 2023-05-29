package com.example.buyerapp.data.network.dto.order_info

import com.example.buyerapp.domain.model.order_info.Order
import com.example.buyerapp.domain.model.order_info.State
import com.example.buyerapp.domain.model.order_info.Store
import java.util.Date

data class OrderRes(
    val id: Long,
    val price: Int,
    val regDate: Date,
    val state: StateRes,
    val store: StoreRes
)

data class StateRes(
    val id: Long,
    val descr: String,
    val color: String
)

data class StoreRes(
    val id: Long,
    val title: String,
    val logo: String,
    val color: String
)

fun OrderRes.toDomain() =
    Order(id, price, regDate, state.toDomain(), store.toDomain())

fun StateRes.toDomain() =
    State(id, descr, color)

fun StoreRes.toDomain() =
    Store(id, title, logo, color)

fun List<OrderRes>.toDomain() = map {
    Order(it.id, it.price, it.regDate, it.state.toDomain(), it.store.toDomain())
}