package com.example.buyerapp.domain.model

class ProductInfo (
    val id: Int,
    val title: String,
    val measureUnit: MeasureUnit,
    val price: Long,
    val description: String,
    val barcode: String
)

data class MeasureUnit(
    val title: String,
    val divValue: Int,
    val step: Int,
    val id: Long
)