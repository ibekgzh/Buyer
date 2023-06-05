package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.usecase.LocalUseCase
import com.example.buyerapp.domain.repository.StoreRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class DeleteCachedStoreUseCase @Inject constructor(private val storeRepository: StoreRepository) :
    LocalUseCase<Unit, Unit>() {
    override suspend fun FlowCollector<Unit>.execute(params: Unit) {
        storeRepository.deleteStore()
        emit(Unit)
    }
}