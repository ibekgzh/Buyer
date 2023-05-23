package com.example.buyerapp.data.repository

import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.user_info.toDomain
import com.example.buyerapp.data.network.dto.user_info.toDto
import com.example.buyerapp.domain.model.UserInfo
import com.example.buyerapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiService: ApiService) : UserRepository {

    override suspend fun getUserInfo(): UserInfo {
        return apiService.getUserInfo().toDomain()
    }

    override suspend fun saveUserInfo(userInfo: UserInfo): UserInfo {
        return apiService.updateUserInfo(userInfo.toDto()).toDomain()
    }
}