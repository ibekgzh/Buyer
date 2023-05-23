package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.UserInfo
import com.example.buyerapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val userRepository: UserRepository): DataStateUseCase<Unit, UserInfo>() {

    override suspend fun FlowCollector<DataState<UserInfo>>.execute(params: Unit) {
        val result = apiCall {
            userRepository.getUserInfo()
        }
        emit(result)
    }
}