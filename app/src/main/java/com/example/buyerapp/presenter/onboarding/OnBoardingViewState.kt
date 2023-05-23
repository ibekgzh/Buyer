package com.example.buyerapp.presenter.onboarding

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.OnBoardingItem

data class OnBoardingViewState (
    val items: List<OnBoardingItem>? = null
)

sealed class OnBoardingEvent {
    object LoadItems : OnBoardingEvent()
    object CheckAuthKey: OnBoardingEvent()
}

sealed class OnBoardingEffect: BaseEffect() {
    object OnNavigateToCellPhone: OnBoardingEffect()
    data class OnNavigateToPinCode(val authKey: String): OnBoardingEffect()
}
