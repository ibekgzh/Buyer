package com.example.buyerapp.presenter.home

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.Store

class HomeViewState(
    val hasChosenStore: Boolean = false,
    val stores: Pageable<Store>? = null
)

sealed class HomeEvent {
    data class SearchStore(
        val init: Boolean = false,
        val searchWord: String = "",
        val pageNum: Int = 0,
        val pageSize: Int = StandardPageSize
    ) : HomeEvent()
    data class ChooseStore(val store: Store) : HomeEvent()
    object GetCachedStore : HomeEvent()
}

sealed class HomeEffect : BaseEffect() {
    object ReOpenHome : HomeEffect()
}


const val StandardPageSize = 20