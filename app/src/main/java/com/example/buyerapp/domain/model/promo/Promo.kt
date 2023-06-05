package com.example.buyerapp.domain.model.promo

import com.example.buyerapp.domain.model.Store

data class Promo (
    val description: String,
    val endDate: String,
    val id: Long,
    val startDate: String,
    val storeRes: Store,
    val title: String,
    val url: String
)