package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.fcm.LastNotificationOfStore
import com.example.buyerapp.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetFilteredNotificationsUseCase @Inject constructor(private val notificationRepository: NotificationRepository):
    DataStateUseCase<GetFilteredNotificationsUseCase.Params, Pageable<LastNotificationOfStore>>() {
    data class Params(
        val storeId: Long,
        val page: Int,
        val pageSize: Int
    )

    override suspend fun FlowCollector<DataState<Pageable<LastNotificationOfStore>>>.execute(params: Params) {
        val result = apiCall {
            notificationRepository.getFilteredNotifications(params.storeId, params.page, params.pageSize)
        }
        emit(result)
    }
}