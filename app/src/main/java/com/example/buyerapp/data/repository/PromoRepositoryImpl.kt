package com.example.buyerapp.data.repository

import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.promo.toDomain
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.promo.Promo
import com.example.buyerapp.domain.repository.PromoRepository
import javax.inject.Inject

class PromoRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    PromoRepository {
    override suspend fun getPromotions(storeId: Int, pageNum: Int, pageSize: Int): Pageable<Promo> {
        return apiService.getPromotion(pageNum, pageSize, storeId).toDomain()
    }
}