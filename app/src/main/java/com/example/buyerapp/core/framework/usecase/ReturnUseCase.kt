package com.example.buyerapp.core.framework.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class ReturnUseCase<in Params, ReturnType> {

    protected abstract suspend fun execute(params: Params): Flow<ReturnType>

    suspend operator fun invoke(params: Params): Flow<ReturnType> = execute(params)
        .flowOn(Dispatchers.IO)
}