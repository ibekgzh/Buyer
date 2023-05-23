package com.example.buyerapp.domain.repository

import com.example.buyerapp.domain.model.OnBoardingItem

interface OnBoardingRepository {

    suspend fun getOnBoarding(): List<OnBoardingItem>
}