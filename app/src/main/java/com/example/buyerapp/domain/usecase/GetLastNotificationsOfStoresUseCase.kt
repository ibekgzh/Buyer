package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.fcm.LastNotificationOfStore
import com.example.buyerapp.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetLastNotificationsOfStoresUseCase @Inject constructor(private val notificationRepository: NotificationRepository):
    DataStateUseCase<Unit, List<LastNotificationOfStore>>() {
    override suspend fun FlowCollector<DataState<List<LastNotificationOfStore>>>.execute(params: Unit) {
        val result = apiCall {
            notificationRepository.getLastNotificationsOfStores()
        }
        emit(result)
    }
}