package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.usecase.LocalUseCase
import com.example.buyerapp.domain.repository.GlobalKeyValueRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class ClearAuthKeyUseCase  @Inject constructor(private val repository: GlobalKeyValueRepository) :
    LocalUseCase<Unit, Unit>() {

    override suspend fun FlowCollector<Unit>.execute(params: Unit) {
        repository.clearAuthKey()
        emit(Unit)
    }
}