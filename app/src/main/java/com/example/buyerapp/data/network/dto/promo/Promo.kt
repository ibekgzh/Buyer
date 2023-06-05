package com.example.buyerapp.data.network.dto.promo

import com.example.buyerapp.data.network.dto.PageableContent
import com.example.buyerapp.data.network.dto.store.StoreRes
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.Store
import com.example.buyerapp.domain.model.promo.Promo

data class PromoRes(
    val description: String,
    val endDate: String,
    val id: Long,
    val startDate: String,
    val store: StoreRes,
    val title: String,
    val url: String
)

fun PromoRes.toDomain() =
    Promo(
        description,
        endDate,
        id,
        startDate,
        store.toDomain(),
        title,
        url
    )

fun List<PromoRes>.toDomain() = map { it.toDomain() }

fun StoreRes.toDomain() = Store(id, title, logo, color)

fun PageableContent<PromoRes>.toDomain() = Pageable(
    content?.toDomain() ?: listOf(),
    totalPages,
    number
)