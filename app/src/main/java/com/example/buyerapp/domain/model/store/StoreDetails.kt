package com.example.buyerapp.domain.model.store

data class StoreDetails(
    val id: Int,
    val title: String,
    val descr: String,
    val color: String,
    val extId: String?,
    val largeLogo: String,
    val logo: String,
    val notify: Boolean
)