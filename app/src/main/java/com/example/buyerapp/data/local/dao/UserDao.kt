package com.example.buyerapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.buyerapp.core.framework.local.BaseDao
import com.example.buyerapp.data.local.entity.UserEntity

@Dao
interface UserDao : BaseDao<UserEntity> {
    @Query("SELECT * FROM ${UserEntity.TABLE_NAME}")
    suspend fun getUser(): UserEntity?
}