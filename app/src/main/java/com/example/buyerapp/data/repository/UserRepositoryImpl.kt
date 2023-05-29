package com.example.buyerapp.data.repository

import com.example.buyerapp.data.local.dao.UserDao
import com.example.buyerapp.data.local.entity.toDomain
import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.user_info.toDomain
import com.example.buyerapp.data.network.dto.user_info.toDto
import com.example.buyerapp.data.network.dto.user_info.toEntity
import com.example.buyerapp.domain.model.UserInfo
import com.example.buyerapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUserInfo(): UserInfo {
        val userLocal = userDao.getUser()
        userLocal?.let {
            return it.toDomain()
        }
        val user = apiService.getUserInfo()
        userDao.insert(user.toEntity())
        return user.toDomain()
    }

    override suspend fun saveUserInfo(userInfo: UserInfo): UserInfo {
        return apiService.updateUserInfo(userInfo.toDto()).toDomain()
    }
}