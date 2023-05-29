package com.example.buyerapp.domain.model.order_info

import java.util.Date

data class Order(
    val id: Long,
    val price: Int,
    val regDate: Date,
    val state: State,
    val store: Store
)

data class State(
    val id: Long,
    val descr: String,
    val color: String
)

data class Store(
    val id: Long,
    val title: String,
    val logo: String,
    val color: String
)