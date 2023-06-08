package com.example.buyerapp.domain.repository

import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.Store
import com.example.buyerapp.domain.model.store.StoreDetails

interface StoreRepository {
    suspend fun search(search: String, pageNum: Int, pageSize: Int): Pageable<Store>
    suspend fun saveStore(store: Store)
    suspend fun getStore(): Store?
    suspend fun deleteStore()
    suspend fun getStoreDetails(id: Long): StoreDetails
    suspend fun storeNotifyState(active: Boolean, storeId: Long)
}