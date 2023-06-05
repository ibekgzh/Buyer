package com.example.buyerapp.data.repository

import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.order_info.toDomain
import com.example.buyerapp.data.network.dto.order_info.toOrderDto
import com.example.buyerapp.domain.model.Cart
import com.example.buyerapp.domain.model.order_info.Order
import com.example.buyerapp.domain.model.order_info.OrderDetails
import com.example.buyerapp.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(val apiService: ApiService) : OrderRepository {
    override suspend fun getOrders(
        startDate: String,
        endDate: String,
        pageNum: Int,
        pageSize: Int
    ): List<Order> {
        val result = apiService.getOrders(endDate, pageNum, pageSize, startDate)
        return result.content?.toDomain() ?: listOf()
    }

    override suspend fun order(cart: Cart): Int {
        return apiService.order(cart.toOrderDto()).id
    }

    override suspend fun getOrderDetails(id: Long): OrderDetails {
        return apiService.orderDetails(id).toDomain()
    }
}