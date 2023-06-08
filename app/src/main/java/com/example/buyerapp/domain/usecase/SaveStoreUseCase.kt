package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.usecase.LocalUseCase
import com.example.buyerapp.domain.model.Store
import com.example.buyerapp.domain.repository.StoreRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class SaveStoreUseCase @Inject constructor(private val storeRepository: StoreRepository) :
    LocalUseCase<SaveStoreUseCase.Params, Unit>() {

    data class Params(
        val id: Long,
        val title: String,
        val descr: String,
        val color: String
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        storeRepository.saveStore(Store(params.id, params.title, "", params.color))
        emit(Unit)
    }
}