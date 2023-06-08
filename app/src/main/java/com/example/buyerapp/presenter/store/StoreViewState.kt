package com.example.buyerapp.presenter.store

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.store.StoreDetails

data class StoreViewState(
    val storeDetails: StoreDetails
)

sealed class StoreEvent {
    data class LoadDetails(val id: Long) : StoreEvent()
    object ChooseStore : StoreEvent()
    data class ChangeNotifyState(val active: Boolean, val storeId: Long) : StoreEvent()
}

sealed class StoreEffect : BaseEffect() {
    object OpenHome : StoreEffect()
}
