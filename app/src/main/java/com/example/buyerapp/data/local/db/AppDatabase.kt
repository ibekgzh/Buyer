package com.example.buyerapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.buyerapp.data.local.dao.StoreDao
import com.example.buyerapp.data.local.dao.UserDao
import com.example.buyerapp.data.local.entity.StoreEntity
import com.example.buyerapp.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, StoreEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getStoreDao(): StoreDao
}