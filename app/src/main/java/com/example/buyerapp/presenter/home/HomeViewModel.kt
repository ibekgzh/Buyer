package com.example.buyerapp.presenter.home

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.model.Store
import com.example.buyerapp.domain.usecase.GetSearchStoreUseCase
import com.example.buyerapp.domain.usecase.GetCachedStoreUseCase
import com.example.buyerapp.domain.usecase.SaveStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSearchStoreUseCase: GetSearchStoreUseCase,
    private val saveStoreUseCase: SaveStoreUseCase,
    private val getCachedStoreUseCase: GetCachedStoreUseCase
) :
    MviViewModel<HomeViewState, HomeEvent, HomeEffect>() {
    override fun onTriggerEvent(eventType: HomeEvent) {
        when (eventType) {
            is HomeEvent.SearchStore ->
                if (eventType.init)
                    onInitSearchStore(
                        eventType.searchWord,
                        eventType.pageNum,
                        eventType.pageSize
                    )
                else onSearchStore(
                    eventType.searchWord,
                    eventType.pageNum,
                    eventType.pageSize
                )

            is HomeEvent.ChooseStore -> onChooseStore(eventType.store)
            is HomeEvent.GetCachedStore -> onGetCachedStore()
        }
    }

    private fun onInitSearchStore(searchWord: String, pageNum: Int, pageSize: Int) = safeLaunch {
        execute(
            getSearchStoreUseCase(
                GetSearchStoreUseCase.Params(
                    searchWord,
                    pageNum,
                    pageSize
                )
            )
        ) {
            setState(HomeViewState(stores = it))
        }
    }

    private fun onSearchStore(searchWord: String, pageNum: Int, pageSize: Int) = safeLaunch {
        executeWithBackPressure(
            getSearchStoreUseCase(
                GetSearchStoreUseCase.Params(
                    searchWord,
                    pageNum,
                    pageSize
                )
            ),
            700
        ) {
            setState(HomeViewState(stores = it))
        }
    }

    private fun onChooseStore(store: Store) = safeLaunch {
        call(saveStoreUseCase(SaveStoreUseCase.Params(store.id, store.title, "", store.color))) {
            effectChannel.trySend(HomeEffect.ReOpenHome)
        }
    }

    private fun onGetCachedStore() = safeLaunch {
        call(getCachedStoreUseCase(Unit)) {
            if (it != null) {
                setState(HomeViewState(hasChosenStore = true))
            } else {
                onTriggerEvent(HomeEvent.SearchStore(true))
            }
        }
    }
}