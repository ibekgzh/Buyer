package com.example.buyerapp.presenter.promo

import com.example.buyerapp.core.constants.StandardPageSize
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.Pageable
import com.example.buyerapp.domain.model.promo.Promo

data class PromoViewState(
    val promos: Pageable<Promo>,
)

sealed class PromoEvent() {
    data class LoadPromos(
        val storeId: Long,
        val page: Int = 0,
        val pageSize: Int = StandardPageSize
    ) : PromoEvent()
}

sealed class PromoEffect : BaseEffect() {

}