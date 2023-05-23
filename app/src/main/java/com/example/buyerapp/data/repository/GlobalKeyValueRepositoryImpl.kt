package com.example.buyerapp.data.repository

import com.example.buyerapp.data.local.GlobalKeyValueCache
import com.example.buyerapp.domain.repository.GlobalKeyValueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GlobalKeyValueRepositoryImpl @Inject constructor(private val globalKeyValueCache: GlobalKeyValueCache) :
    GlobalKeyValueRepository {

    override suspend fun putAuthKey(key: String) {
        globalKeyValueCache.putAuthKey(key)
    }

    override suspend fun getAuthKey(): Flow<String?> {
        return globalKeyValueCache.getAuthKey()
    }

    override suspend fun clearAuthKey() {
        return globalKeyValueCache.cleaAuthKey()
    }
}