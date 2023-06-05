package com.example.buyerapp.domain.model

data class Cart(
    val items: List<CartItem>,
    val totalPrice: Int,
    val store: Store?
)

data class CartItem(
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