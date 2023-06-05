package com.example.buyerapp.presenter.home.tabs.main

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.DeleteCachedStoreUseCase
import com.example.buyerapp.domain.usecase.GetCachedStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val deleteCachedStoreUseCase: DeleteCachedStoreUseCase,
    private val getCachedStoreUseCase: GetCachedStoreUseCase
) :
    MviViewModel<MainViewState, MainEvent, MainEffect>() {
    override fun onTriggerEvent(eventType: MainEvent) {
        when (eventType) {
            is MainEvent.SelectShop -> onSelectShop()
            is MainEvent.GetCachedStore -> onGetCachedStore()
        }
    }

    private fun onSelectShop() = safeLaunch {
        call(deleteCachedStoreUseCase(Unit)) {
            effectChannel.trySend(MainEffect.DeletedCachedShop)
        }
    }

    private fun onGetCachedStore() = safeLaunch {
        call(getCachedStoreUseCase(Unit)) {
            it?.let {
                setState(MainViewState(store = it))
            }
        }
    }
}