package com.example.buyerapp.data.network.dto.auth

data class PinChangeReq(
    val oldPin: String,
    val newPin: String,
)

