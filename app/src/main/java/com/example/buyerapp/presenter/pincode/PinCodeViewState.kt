package com.example.buyerapp.presenter.pincode

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.UserInfo

data class PinCodeViewState(
    val userInfo: UserInfo
)

sealed class PinCodeEvent {
    data class PinCheck(val pin: String) : PinCodeEvent()
    object Logout : PinCodeEvent()
    object GetUserInfo : PinCodeEvent()
    data class PinChange(val oldPin: String, val newPin: String) : PinCodeEvent()
}

sealed class PinCodeEffect : BaseEffect() {
    object OnNavigateHome : PinCodeEffect()
    object OnNavigateUp : PinCodeEffect()
    object OnNavigateUpProfile : PinCodeEffect()
}