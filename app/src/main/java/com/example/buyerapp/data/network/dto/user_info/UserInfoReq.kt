package com.example.buyerapp.data.network.dto.user_info

import com.example.buyerapp.domain.model.UserInfo

data class UserInfoReq(
    private val firstname: String?,
    private val lastname: String?
)

fun UserInfo.toDto() =
    UserInfoReq(firstname, lastname)