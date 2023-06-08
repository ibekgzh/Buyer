package com.example.buyerapp.domain.repository

import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.fcm.LastNotificationOfStore

interface NotificationRepository {
    suspend fun getLastNotificationsOfStores(): List<LastNotificationOfStore>

    suspend fun getFilteredNotifications(storeId: Long, pageNum: Int, pageSize: Int): Pageable<LastNotificationOfStore>
}