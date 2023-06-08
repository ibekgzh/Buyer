package com.example.buyerapp.presenter.notify

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.fcm.LastNotificationOfStore

data class LastNotificationsOfStoresViewState (
    val notifications: List<LastNotificationOfStore>
)

sealed class LastNotificationsOfStoresEvent {
    object LoadNotifications: LastNotificationsOfStoresEvent()
}

sealed class LastNotificationsOfStoresEffect : BaseEffect(){

}
