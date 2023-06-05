package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.usecase.ReturnUseCase
import com.example.buyerapp.domain.model.Store
import com.example.buyerapp.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCachedStoreUseCase @Inject constructor(private val storeRepository: StoreRepository):
    ReturnUseCase<Unit, Store?>() {
    override suspend fun execute(params: Unit): Flow<Store?> = flow {
        emit(storeRepository.getStore())
    }
}