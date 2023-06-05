package com.example.buyerapp.data.repository

import com.example.buyerapp.core.framework.extension.classTag
import com.example.buyerapp.data.local.dao.StoreDao
import com.example.buyerapp.data.local.entity.StoreEntity
import com.example.buyerapp.data.local.entity.toDomain
import com.example.buyerapp.data.local.entity.toEntity
import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.StoreNotifyStateReq
import com.example.buyerapp.data.network.dto.store.toDomain
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.Store
import com.example.buyerapp.domain.model.store.StoreDetails
import com.example.buyerapp.domain.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val storeDao: StoreDao
) : StoreRepository {

    override suspend fun search(search: String, pageNum: Int, pageSize: Int): Pageable<Store> {
        return apiService.search(search, pageNum, pageSize).toDomain()
    }

    override suspend fun saveStore(store: Store) {
        storeDao.insert(store.toEntity())
    }

    override suspend fun getStore(): Store? {
        return storeDao.getStore()?.toDomain()
    }

    override suspend fun deleteStore() {
        storeDao.deleteAll()
    }

    override suspend fun getStoreDetails(id: Int): StoreDetails {
        return apiService.getStoreDetails(id).toDomain()
    }

    override suspend fun storeNotifyState(active: Boolean, storeId: Int) {
        return apiService.storeNotifyState(StoreNotifyStateReq(active, storeId))
    }
}