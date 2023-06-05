package com.example.buyerapp.data.repository

import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.CartItemDeleteReq
import com.example.buyerapp.data.network.dto.CartItemModifyReq
import com.example.buyerapp.data.network.dto.toDomain
import com.example.buyerapp.domain.model.Cart
import com.example.buyerapp.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(val apiService: ApiService) : CartRepository {
    override suspend fun getCart(): Cart {
        return apiService.getCart().toDomain()
    }

    override suspend fun modifyCartItem(amount: Int, itemId: Int) {
        return apiService.modifyCartItem(CartItemModifyReq(amount, itemId))
    }

    override suspend fun deleteCartItem(amount: Int, itemId: Int) {
        return apiService.deleteCartItem(CartItemDeleteReq(amount, itemId))
    }
}