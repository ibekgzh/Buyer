package com.example.buyerapp.data.network.dto.order_info

data class OrderCreateRes (
    val id: Int,
    val price: Int,
    val regDate: String,
    val stateRes: StateRes,
    val storeRes: StoreRes
)


