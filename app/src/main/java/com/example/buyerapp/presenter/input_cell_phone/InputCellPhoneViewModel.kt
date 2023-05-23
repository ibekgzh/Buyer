package com.example.buyerapp.presenter.input_cell_phone

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.AuthStartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputCellPhoneViewModel @Inject constructor(private val authStartUseCase: AuthStartUseCase) :
    MviViewModel<InputCellPhoneViewState, InputCellPhoneEvent, InputCellPhoneEffect>() {

    override fun onTriggerEvent(eventType: InputCellPhoneEvent) {
        when (eventType) {
            is InputCellPhoneEvent.OnSendPhoneToReceiveOtp -> onSendPhoneToReceiveOtp(eventType.cellPhone)
        }
    }

    private fun onSendPhoneToReceiveOtp(cellPhone: String) = safeLaunch {
        execute(authStartUseCase(AuthStartUseCase.Params(cellPhone))) {
            effectChannel.trySend(InputCellPhoneEffect.OnNavigateConfirmCellPhone(cellPhone))
        }
    }
}