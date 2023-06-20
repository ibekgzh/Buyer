package com.example.buyerapp.data.network.dto

import com.example.buyerapp.domain.model.ProductInfo
import com.example.buyerapp.domain.model.MeasureUnit as DomainMeasureUnit
data class ProductInfoRes(
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

fun MeasureUnit.toDomain() =
    DomainMeasureUnit(title, divValue, step, id)

fun ProductInfoRes.toDomain() =
    ProductInfo(
        id,
        title,
        measureUnit = measureUnit.toDomain(),
        price,
        description,
        barcode)
