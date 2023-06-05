package com.example.buyerapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.buyerapp.core.framework.local.BaseDao
import com.example.buyerapp.data.local.entity.StoreEntity

@Dao
interface StoreDao: BaseDao<StoreEntity> {
    @Query("SELECT * FROM ${StoreEntity.TABLE_NAME}")
    suspend fun getStore(): StoreEntity?

    @Query("DELETE FROM ${StoreEntity.TABLE_NAME}")
    suspend fun deleteAll()
}