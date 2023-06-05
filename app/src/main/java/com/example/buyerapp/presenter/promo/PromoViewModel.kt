package com.example.buyerapp.presenter.promo

import coil.ImageLoader
import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.GetPromotionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PromoViewModel @Inject constructor(
    private val getPromotionsUseCase: GetPromotionsUseCase,
    val imageLoader: ImageLoader
) :
    MviViewModel<PromoViewState, PromoEvent, PromoEffect>() {
    override fun onTriggerEvent(eventType: PromoEvent) {
        when (eventType) {
            is PromoEvent.LoadPromos -> onLoadPromos(
                eventType.storeId,
                eventType.page,
                eventType.pageSize
            )
        }
    }

    private fun onLoadPromos(storeId: Int, page: Int, pageSize: Int) = safeLaunch {
        execute(getPromotionsUseCase(GetPromotionsUseCase.Params(storeId, page, pageSize))) {
            setState(PromoViewState(promos = it))
        }
    }
}