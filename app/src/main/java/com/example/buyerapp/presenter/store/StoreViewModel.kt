package com.example.buyerapp.presenter.store

import coil.ImageLoader
import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.DeleteCachedStoreUseCase
import com.example.buyerapp.domain.usecase.GetStoreDetailsUseCase
import com.example.buyerapp.domain.usecase.StoreNotifyStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val getStoreDetailsUseCase: GetStoreDetailsUseCase,
    private val deleteCachedStoreUseCase: DeleteCachedStoreUseCase,
    private val storeNotifyStateUseCase: StoreNotifyStateUseCase,
    val imageLoader: ImageLoader):
    MviViewModel<StoreViewState, StoreEvent, StoreEffect>(){
    override fun onTriggerEvent(eventType: StoreEvent) {
        when(eventType) {
            is StoreEvent.LoadDetails -> onLoadDetails(eventType.id)
            is StoreEvent.ChooseStore -> onChooseStore()
            is StoreEvent.ChangeNotifyState -> onChangeNotifyState(eventType.active, eventType.storeId)
        }
    }

    private fun onLoadDetails(id: Int) = safeLaunch {
        execute(getStoreDetailsUseCase(id)) {
            setState(StoreViewState(storeDetails = it))
        }
    }

    private fun onChooseStore() = safeLaunch {
        call(deleteCachedStoreUseCase(Unit)) {
            effectChannel.trySend(StoreEffect.OpenHome)
        }
    }

    private fun onChangeNotifyState(active: Boolean, storeId: Int) = safeLaunch {
        execute(storeNotifyStateUseCase(StoreNotifyStateUseCase.Params(active, storeId))) {
            setState(StoreViewState(storeDetails = it))
        }
    }
}