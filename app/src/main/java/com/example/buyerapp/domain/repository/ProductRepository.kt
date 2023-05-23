package com.example.buyerapp.domain.repository

import com.example.buyerapp.domain.model.ProductInfo

interface ProductRepository {
    suspend fun getProductInfo(barcode: String, storeId: Long, uid: String?): ProductInfo

    suspend fun addProduct(amount: String, itemId: String)
}