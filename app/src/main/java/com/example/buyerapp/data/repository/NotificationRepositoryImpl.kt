package com.example.buyerapp.data.repository

import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.fcm.toDomain
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.fcm.LastNotificationOfStore
import com.example.buyerapp.domain.repository.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    NotificationRepository {
    override suspend fun getLastNotificationsOfStores(): List<LastNotificationOfStore> {
        return apiService.getLastNotificationsOfStores().toDomain()
    }

    override suspend fun getFilteredNotifications(
        storeId: Long,
        pageNum: Int,
        pageSize: Int
    ): Pageable<LastNotificationOfStore> {
        return apiService.getNotificationFilter(storeId, pageNum, pageSize).toDomain()
    }
}