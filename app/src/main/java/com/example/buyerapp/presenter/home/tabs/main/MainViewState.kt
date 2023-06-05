package com.example.buyerapp.presenter.home.tabs.main

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.Store

data class MainViewState (
    val store: Store
)

sealed class MainEvent {
    object GetCachedStore: MainEvent()
    object SelectShop: MainEvent()
}

sealed class MainEffect : BaseEffect(){
    object DeletedCachedShop: MainEffect()
}