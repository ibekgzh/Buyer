package com.example.buyerapp.data.repository

import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.Product
import com.example.buyerapp.data.network.dto.toDomain
import com.example.buyerapp.domain.model.ProductInfo
import com.example.buyerapp.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ProductRepository {
    override suspend fun getProductInfo(barcode: String, storeId: Long, uid: String?): ProductInfo {
        return apiService.getProductInfo(barcode, storeId, uid).toDomain()
    }

    override suspend fun addProduct(amount: String, itemId: String) {
        return apiService.addProduct(Product(amount, itemId))
    }

}