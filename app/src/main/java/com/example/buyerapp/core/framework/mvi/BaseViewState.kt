package com.example.buyerapp.core.framework.mvi

data class BaseViewState<T> (
    var isLoading : Boolean = false,
    var state : T? = null
)

open class BaseEffect {
    data class OnError(val message: String): BaseEffect()
}