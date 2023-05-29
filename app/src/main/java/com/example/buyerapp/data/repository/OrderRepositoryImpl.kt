package com.example.buyerapp.data.repository

import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.order_info.toDomain
import com.example.buyerapp.domain.model.order_info.Order
import com.example.buyerapp.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(val apiService: ApiService) : OrderRepository {
    override suspend fun getOrders(
        startDate: String,
        endDate: String,
        pageNum: Int,
        pageSize: Int
    ): List<Order> {
        return apiService.getOrders(startDate, endDate, pageNum, pageSize).toDomain()
    }
}