package com.example.buyerapp.presenter.pincode

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.UserInfo

data class PinCodeViewState(
    val userInfo: UserInfo
)

sealed class PinCodeEvent {
    data class PinCheck(val pin: String): PinCodeEvent()
    object Logout: PinCodeEvent()
    object GetUserInfo: PinCodeEvent()
}

sealed class PinCodeEffect: BaseEffect() {
    object OnPinChecked: PinCodeEffect()
    object OnLogout: PinCodeEffect()
}