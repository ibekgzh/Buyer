package com.example.buyerapp.domain.repository

import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.promo.Promo

interface PromoRepository {
    suspend fun getPromotions(storeId: Long, pageNum: Int, pageSize: Int): Pageable<Promo>
}