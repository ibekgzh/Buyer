package com.example.buyerapp.data.network.dto.auth

data class PinChangeReq(
    val newPin: String,
    val oldPin: String
)

