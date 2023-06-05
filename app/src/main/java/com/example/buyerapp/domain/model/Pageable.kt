package com.example.buyerapp.domain.model

data class Pageable<T> (
    val data: List<T>,
    val totalPage: Int,
    val currentPage: Int
)