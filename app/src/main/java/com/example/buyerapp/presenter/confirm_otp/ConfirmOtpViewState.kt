package com.example.buyerapp.presenter.confirm_otp

import com.example.buyerapp.core.framework.mvi.BaseEffect

class ConfirmOtpViewState {

}

sealed class ConfirmOtpEvent {
    data class AuthStart(val cellphone: String): ConfirmOtpEvent()
    object PinResetSms: ConfirmOtpEvent()
}

sealed class ConfirmOtpEffect: BaseEffect() {
}