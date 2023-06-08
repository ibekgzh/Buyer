package com.example.buyerapp.presenter.notify.store_filter.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.buyerapp.domain.model.fcm.LastNotificationOfStore

@Composable
fun FilteredNotificationItem(lastNotificationOfStore: LastNotificationOfStore) {

    Text(text = lastNotificationOfStore.body)
}