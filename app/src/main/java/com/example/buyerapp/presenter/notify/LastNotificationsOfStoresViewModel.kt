package com.example.buyerapp.presenter.notify

import coil.ImageLoader
import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.GetLastNotificationsOfStoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LastNotificationsOfStoresViewModel @Inject constructor(
    private val getLastNotificationsOfStoresUseCase: GetLastNotificationsOfStoresUseCase,
    val imageLoader: ImageLoader
) :
    MviViewModel<LastNotificationsOfStoresViewState, LastNotificationsOfStoresEvent, LastNotificationsOfStoresEffect>() {
    override fun onTriggerEvent(eventType: LastNotificationsOfStoresEvent) {
        when (eventType) {
            LastNotificationsOfStoresEvent.LoadNotifications -> onLoadNotifications()
        }
    }

    private fun onLoadNotifications() = safeLaunch {
        execute(getLastNotificationsOfStoresUseCase(Unit)) {
            setState(LastNotificationsOfStoresViewState(notifications = it))
        }
    }
}