package com.example.buyerapp.data.di.local

import android.content.Context
import androidx.room.Room
import com.example.buyerapp.BuildConfig
import com.example.buyerapp.data.local.GlobalKeyValueCache
import com.example.buyerapp.data.local.dao.StoreDao
import com.example.buyerapp.data.local.dao.UserDao
import com.example.buyerapp.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


private const val DB_NAME = "db_name"

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {

    @Singleton
    @Provides
    fun provideGlobalKeyValueCache(@ApplicationContext context: Context): GlobalKeyValueCache =
        GlobalKeyValueCache(context)

    @Provides
    @Singleton
    @Named(value = DB_NAME)
    fun provideDatabaseName(): String {
        return BuildConfig.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.getUserDao()
    }

    @Provides
    @Singleton
    fun provideStoreDao(appDatabase: AppDatabase): StoreDao {
        return appDatabase.getStoreDao()
    }
}