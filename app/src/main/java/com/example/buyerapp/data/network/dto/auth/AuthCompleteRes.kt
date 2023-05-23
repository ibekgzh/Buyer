package com.example.buyerapp.data.network.dto.auth

import com.example.buyerapp.domain.model.AuthComplete

data class AuthCompleteRes(
    val key: String
)

fun AuthCompleteRes.toDomain() =
    AuthComplete(key = key)