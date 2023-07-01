package com.example.buyerapp.presenter.pincode

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.GetUserInfoUseCase
import com.example.buyerapp.domain.usecase.LogoutUseCase
import com.example.buyerapp.domain.usecase.PinChangeUseCase
import com.example.buyerapp.domain.usecase.PinCheckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    val getUserInfoUseCase: GetUserInfoUseCase,
    val pinCheckUseCase: PinCheckUseCase,
    val pinChangeUseCase: PinChangeUseCase,
    val logoutUseCase: LogoutUseCase
) : MviViewModel<PinCodeViewState, PinCodeEvent, PinCodeEffect>() {
    override fun onTriggerEvent(eventType: PinCodeEvent) {
        when(eventType) {
            is PinCodeEvent.GetUserInfo -> onGetUserInfo()
            is PinCodeEvent.PinCheck -> onPinChecked(eventType.pin)
            is PinCodeEvent.PinChange -> onPinChanged(eventType.oldPin, eventType.newPin)
            is PinCodeEvent.Logout -> onLogout()
        }
    }

    private fun onGetUserInfo() = safeLaunch {
        execute(getUserInfoUseCase(Unit)) {
            setState(PinCodeViewState(userInfo = it))
        }
    }

    private fun onPinChecked(pin: String) = safeLaunch {
        execute(pinCheckUseCase(pin)){
            effectChannel.trySend(PinCodeEffect.OnNavigateHome)
        }
    }

    private fun onPinChanged(oldPin: String, newPin: String) = safeLaunch {
        execute(pinChangeUseCase(PinChangeUseCase.Params(oldPin, newPin))) {
            effectChannel.trySend(PinCodeEffect.OnNavigateUpProfile)
        }
    }

    private fun onLogout() = safeLaunch {
        execute(logoutUseCase(Unit)){
            effectChannel.trySend(PinCodeEffect.OnLogout)
        }
    }
}