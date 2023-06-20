package com.example.buyerapp.domain.model.fcm

import com.example.buyerapp.domain.model.Store

data class LastNotificationOfStore (
    val body: String,
    val id: Long,
    val imageUrl: String,
    val sentDate: String,
    val sentTime: String,
    val store: Store?,
    val title: String
)