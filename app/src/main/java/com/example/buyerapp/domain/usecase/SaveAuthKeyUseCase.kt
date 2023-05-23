package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.usecase.LocalUseCase
import com.example.buyerapp.domain.repository.GlobalKeyValueRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class SaveAuthKeyUseCase @Inject constructor(private val repository: GlobalKeyValueRepository) :
    LocalUseCase<SaveAuthKeyUseCase.Params, Unit>() {

    data class Params(
        val key: String
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.putAuthKey(params.key)
        emit(Unit)
    }
}