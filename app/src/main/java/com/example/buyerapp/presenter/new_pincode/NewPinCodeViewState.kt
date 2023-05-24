package com.example.buyerapp.presenter.new_pincode

import com.example.buyerapp.core.framework.mvi.BaseEffect

data class NewPinCodeViewState(
    val newPin: String
)

sealed class NewPinCodeEvent {
    data class Complete(val smsToken: String, val cellPhone: String, val newPin: String) :
        NewPinCodeEvent()
    data class ResetConfirm(val pinOtp: String, val pin: String): NewPinCodeEvent()
}

sealed class NewPinCodeEffect : BaseEffect() {
    object OnNavigateHome : NewPinCodeEffect()
}