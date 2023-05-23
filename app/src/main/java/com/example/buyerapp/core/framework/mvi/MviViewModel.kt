package com.example.buyerapp.core.framework.mvi

import com.example.buyerapp.core.framework.mvvm.MvvmViewModel
import com.example.buyerapp.core.framework.network.Failure
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class MviViewModel<STATE, EVENT, EFFECT: BaseEffect> : MvvmViewModel() {

    private val _uiState = MutableStateFlow<BaseViewState<STATE>>(BaseViewState())
    val uiState = _uiState.asStateFlow()

    protected val effectChannel = Channel<BaseEffect>(Channel.UNLIMITED)
    val effect: Flow<BaseEffect> = effectChannel.receiveAsFlow()

    abstract fun onTriggerEvent(eventType: EVENT)

    protected fun getState(): STATE {
        return _uiState.value.state!!
    }

    protected fun setState(state: STATE) = safeLaunch {
        _uiState.update {
            it.copy(isLoading = false, state = state)
        }
    }

    override fun startLoading() {
        super.startLoading()
        _uiState.update {
            it.copy(isLoading = true)
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        _uiState.update {
            it.copy(isLoading = false)
        }
        if(exception is Failure.ApiError){
            effectChannel.trySend(BaseEffect.OnError(exception.message))
        }
        else {
            effectChannel.trySend(BaseEffect.OnError("Unknown Error"))
        }
    }
}