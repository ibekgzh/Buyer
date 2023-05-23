package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.usecase.ReturnUseCase
import com.example.buyerapp.domain.repository.GlobalKeyValueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthKeyUseCase @Inject constructor(private val globalKeyValueRepository: GlobalKeyValueRepository):
    ReturnUseCase<Unit, String?>(){

    override suspend fun execute(params: Unit): Flow<String?> {
        return globalKeyValueRepository.getAuthKey();
    }

}