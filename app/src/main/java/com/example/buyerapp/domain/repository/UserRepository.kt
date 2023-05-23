package com.example.buyerapp.domain.repository

import com.example.buyerapp.domain.model.UserInfo

interface UserRepository {

    suspend fun getUserInfo(): UserInfo

    suspend fun saveUserInfo(userInfo: UserInfo): UserInfo
}