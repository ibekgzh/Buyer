package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.OnBoardingItem
import com.example.buyerapp.domain.repository.OnBoardingRepository
import com.example.buyerapp.core.framework.network.apiCall
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetOnBoardingItemsUseCase @Inject constructor(
    private val repository: OnBoardingRepository
) : DataStateUseCase<Unit, List<OnBoardingItem>>() {

    override suspend fun FlowCollector<DataState<List<OnBoardingItem>>>.execute(params: Unit) {
        val items = apiCall {
            repository.getOnBoarding();
        }

        emit(items)
    }
}