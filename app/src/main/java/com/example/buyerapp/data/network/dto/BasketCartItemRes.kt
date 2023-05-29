package com.example.buyerapp.data.network.dto

data class BasketCartItemRes(
    val items: List<Cart>,
    val totalPrice: Int,
    val store: Store
)

data class Cart(
    val item: Item,
    val amount: Int,
    val price: Int
)

data class Item(
    val id: Int,
    val title: String,
    val measureUnit: MeasureUnit,
    val price: Int,
    val description: String,
    val barcode: String,
    val uid: String
)

data class Store(
    val id: Int,
    val title: String,
    val logo: String,
    val color: String
)