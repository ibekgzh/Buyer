package com.example.buyerapp.data.network.dto.store

import com.example.buyerapp.data.network.dto.PageableContent
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.Store
import com.example.buyerapp.domain.model.store.StoreDetails

data class StoreRes(
    val color: String,
    val id: Int,
    val logo: String,
    val title: String
)

data class StoreDetailsRes(
    val id: Int,
    val title: String,
    val description: String,
    val color: String,
    val extId: String?,
    val largeLogo: String,
    val logo: String,
    val notify: Boolean
)

fun StoreRes.toDomain() =
    Store(id, title, logo, color)

fun StoreDetailsRes.toDomain() = StoreDetails(
    id,
    title,
    descr = description,
    color,
    extId,
    largeLogo,
    logo,
    notify
)

fun List<StoreRes>.toDomain() = map { it.toDomain() }

fun PageableContent<StoreRes>.toDomain() = Pageable(
    content?.toDomain() ?: listOf(),
    totalPages,
    number
)