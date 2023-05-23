package com.example.buyerapp.presenter.input_cell_phone

import com.example.buyerapp.core.framework.mvi.BaseEffect

data class InputCellPhoneViewState(
    val cellPhone: String
)

sealed class InputCellPhoneEvent {
    data class OnSendPhoneToReceiveOtp(val cellPhone: String): InputCellPhoneEvent()
}

sealed class InputCellPhoneEffect : BaseEffect() {
    data class OnNavigateConfirmCellPhone(val cellphone: String) : InputCellPhoneEffect()
}