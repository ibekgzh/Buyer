package com.example.buyerapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface GlobalKeyValueRepository {

    suspend fun putAuthKey(key: String)

    suspend fun getAuthKey(): Flow<String?>

    suspend fun clearAuthKey()
}