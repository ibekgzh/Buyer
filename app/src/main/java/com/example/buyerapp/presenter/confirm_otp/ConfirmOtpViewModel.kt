package com.example.buyerapp.presenter.confirm_otp

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.AuthStartUseCase
import com.example.buyerapp.domain.usecase.PinResetSmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfirmOtpViewModel @Inject constructor(
    val authStartUseCase: AuthStartUseCase,
    val pinResetSmsUseCase: PinResetSmsUseCase
): MviViewModel<ConfirmOtpViewState, ConfirmOtpEvent, ConfirmOtpEffect>() {

    override fun onTriggerEvent(eventType: ConfirmOtpEvent) {
        when(eventType) {
            is ConfirmOtpEvent.AuthStart -> onAuthStart(eventType.cellphone)
            is ConfirmOtpEvent.PinResetSms -> onPinResetSms()
        }
    }

    private fun onAuthStart(cellphone: String) = safeLaunch {
        execute(authStartUseCase(AuthStartUseCase.Params(cellphone))) {
            setState(ConfirmOtpViewState())
        }
    }

    private fun onPinResetSms() = safeLaunch {
        execute(pinResetSmsUseCase(Unit)) {
            setState(ConfirmOtpViewState())
        }
    }
}