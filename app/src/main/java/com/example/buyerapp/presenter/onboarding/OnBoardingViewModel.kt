package com.example.buyerapp.presenter.onboarding

import coil.ImageLoader
import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.GetAuthKeyUseCase
import com.example.buyerapp.domain.usecase.GetOnBoardingItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getOnBoardingItemsUseCase: GetOnBoardingItemsUseCase,
    private val getAuthKeyUseCase: GetAuthKeyUseCase,
    val imageLoader: ImageLoader
) : MviViewModel<OnBoardingViewState, OnBoardingEvent, OnBoardingEffect>() {

    override fun onTriggerEvent(eventType: OnBoardingEvent) {
        when (eventType) {
            is OnBoardingEvent.LoadItems -> onLoadItems()
            is OnBoardingEvent.CheckAuthKey -> onCheckedAuthKey()
        }
    }

    private fun onLoadItems() = safeLaunch {
        execute(getOnBoardingItemsUseCase(Unit)) {
            setState(OnBoardingViewState(items = it))
        }
    }

    private fun onCheckedAuthKey() = safeLaunch {
        getAuthKeyUseCase(Unit).collect { authKey ->
            if (authKey.isNullOrEmpty()) {
                effectChannel.trySend(OnBoardingEffect.OnNavigateToCellPhone)
            } else {
                effectChannel.trySend(OnBoardingEffect.OnNavigateToPinCode(authKey))
            }
        }
    }

}