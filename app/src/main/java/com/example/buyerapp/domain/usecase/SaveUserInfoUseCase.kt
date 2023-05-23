package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.UserInfo
import com.example.buyerapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(private val userRepository: UserRepository) :
    DataStateUseCase<SaveUserInfoUseCase.Params, UserInfo>() {

    data class Params(
        val firstname: String,
        val lastname: String,
        val cellphone: String
    )

    override suspend fun FlowCollector<DataState<UserInfo>>.execute(params: Params) {
        val result = apiCall {
            userRepository.saveUserInfo(
                UserInfo(
                    params.firstname,
                    params.lastname,
                    params.cellphone
                )
            )
        }
        emit(result)
    }
}