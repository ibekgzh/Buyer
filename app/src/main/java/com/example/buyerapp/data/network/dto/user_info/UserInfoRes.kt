package com.example.buyerapp.data.network.dto.user_info

import com.example.buyerapp.data.local.entity.UserEntity
import com.example.buyerapp.domain.model.UserInfo

data class UserInfoRes(
    val firstname: String?,
    val lastname: String?,
    val cellphone: String
)

fun UserInfoRes.toDomain() =
    UserInfo(
        firstname ?: "",
        lastname ?: "",
        cellphone
    )

fun UserInfoRes.toEntity() =
    UserEntity(
        firstname = firstname ?: "",
        lastname = lastname ?: "",
        cellphone = cellphone
    )