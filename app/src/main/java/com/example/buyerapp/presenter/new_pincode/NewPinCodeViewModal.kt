package com.example.buyerapp.presenter.new_pincode

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.AuthCompleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewPinCodeViewModal @Inject constructor(private val onAuthCompleteUseCase: AuthCompleteUseCase) :
    MviViewModel<NewPinCodeViewState, NewPinCodeEvent, NewPinCodeEffect>() {

    override fun onTriggerEvent(eventType: NewPinCodeEvent) {
        when (eventType) {
            is NewPinCodeEvent.Complete -> onComplete(
                eventType.smsToken,
                eventType.cellPhone,
                eventType.newPin
            )
        }
    }

    private fun onComplete(smsToken: String, cellPhone: String, newPin: String) = safeLaunch {
        execute(onAuthCompleteUseCase(AuthCompleteUseCase.Params(smsToken, cellPhone, newPin))) {
            effectChannel.trySend(NewPinCodeEffect.OnNavigateHome)
        }
    }
}