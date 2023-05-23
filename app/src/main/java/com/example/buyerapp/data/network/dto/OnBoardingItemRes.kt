package com.example.buyerapp.data.network.dto

import com.example.buyerapp.domain.model.OnBoardingItem

data class OnBoardingItemRes (
    val title: String,
    val description: String,
    val image: String
)

fun OnBoardingItemRes.toDomain() =
    OnBoardingItem(
        title,
        description,
        image
    )

fun List<OnBoardingItemRes>.toDomain() = map {
    it.toDomain()
}