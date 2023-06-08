package com.example.buyerapp.data.network.dto

import com.example.buyerapp.domain.model.Cart
import com.example.buyerapp.domain.model.CartItem
import com.example.buyerapp.domain.model.Item
import com.example.buyerapp.domain.model.Store

data class CartRes(
    val items: List<CartItemRes>?,
    val totalPrice: Int,
    val store: StoreRes?
)

fun CartRes.toDomain() = Cart(
    items?.toDomain() ?: listOf(),
    totalPrice/100,
    store?.toDomain()
)

data class CartItemRes(
    val item: ItemRes,
    val amount: Int,
    val price: Int
)

fun List<CartItemRes>.toDomain() = map { it.toDomain() }
fun CartItemRes.toDomain() = CartItem(item.toDomain(), amount, price)

data class ItemRes(
    val id: Int,
    val title: String,
    val measureUnit: MeasureUnit,
    val price: Int,
    val description: String,
    val barcode: String,
    val uid: String
)

fun ItemRes.toDomain() =
    Item(id, title, measureUnit.toDomain(), price/100, description, barcode, uid)

data class StoreRes(
    val id: Long,
    val title: String,
    val logo: String,
    val color: String
)

fun StoreRes.toDomain() = Store(id, title, logo, color)

data class CartItemModifyReq(
    val amount: Int,
    val itemId: Int
)

data class CartItemDeleteReq(
    val amount: Int,
    val itemId: Int
)
