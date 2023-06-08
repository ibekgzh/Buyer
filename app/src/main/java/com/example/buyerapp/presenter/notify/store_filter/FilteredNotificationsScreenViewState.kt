package com.example.buyerapp.presenter.notify.store_filter

import com.example.buyerapp.core.constants.StandardPageSize
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.fcm.LastNotificationOfStore
import com.example.buyerapp.domain.model.store.StoreDetails

data class FilteredNotificationsViewState (
    val store: StoreDetails,
    val notifications: Pageable<LastNotificationOfStore>
)

sealed class FilteredNotificationEvent {
    data class LoadStoreInfoAndNotifications(
        val storeId: Long,
        val page: Int = 0,
        val pageSize: Int = StandardPageSize
    ): FilteredNotificationEvent()
}

sealed class FilteredNotificationsEffect: BaseEffect() {

}
