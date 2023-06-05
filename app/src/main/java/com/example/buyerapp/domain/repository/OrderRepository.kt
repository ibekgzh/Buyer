package com.example.buyerapp.domain.repository

import com.example.buyerapp.domain.model.Cart
import com.example.buyerapp.domain.model.order_info.Order
import com.example.buyerapp.domain.model.order_info.OrderDetails

interface OrderRepository {
    suspend fun getOrders(startDate: String, endDate: String, pageNum: Int, pageSize: Int): List<Order>

    suspend fun order(cart: Cart): Int

    suspend fun getOrderDetails(id: Long): OrderDetails
}