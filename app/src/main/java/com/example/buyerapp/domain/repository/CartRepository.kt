package com.example.buyerapp.domain.repository

import com.example.buyerapp.domain.model.Cart

interface CartRepository {
    suspend fun getCart(): Cart

    suspend fun modifyCartItem(amount: Int, itemId: Int)

    suspend fun deleteCartItem(amount: Int, itemId: Int)
}