package com.example.buyerapp.data.repository

import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.toDomain
import com.example.buyerapp.domain.model.OnBoardingItem
import com.example.buyerapp.domain.repository.OnBoardingRepository
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(
    private val api: ApiService): OnBoardingRepository {

    override suspend fun getOnBoarding(): List<OnBoardingItem> {
        return api.getOnBoarding().toDomain();
    }
}