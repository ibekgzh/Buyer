package com.example.buyerapp.presenter.notify.store_filter

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.GetFilteredNotificationsUseCase
import com.example.buyerapp.domain.usecase.GetStoreDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilteredNotificationsScreenViewModel @Inject constructor(
    private val getFilteredNotificationsUseCase: GetFilteredNotificationsUseCase,
    private val getStoreDetailsUseCase: GetStoreDetailsUseCase
    ) :
    MviViewModel<FilteredNotificationsViewState, FilteredNotificationEvent, FilteredNotificationsEffect>() {
    override fun onTriggerEvent(eventType: FilteredNotificationEvent) {
        when (eventType) {
            is FilteredNotificationEvent.LoadStoreInfoAndNotifications -> onLoadNotifications(
                eventType.storeId,
                eventType.page,
                eventType.pageSize
            )
        }
    }

    private fun onLoadNotifications(storeId: Long, page: Int, pageSize: Int) = safeLaunch {
        execute(getFilteredNotificationsUseCase(GetFilteredNotificationsUseCase.Params(storeId, page, pageSize))) { notifications ->
            safeLaunch {
                execute(getStoreDetailsUseCase(storeId)) {
                    setState(FilteredNotificationsViewState(notifications = notifications, store = it))
                }
            }
        }
    }
}