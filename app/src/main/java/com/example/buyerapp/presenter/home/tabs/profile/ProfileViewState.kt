package com.example.buyerapp.presenter.home.tabs.profile

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.UserInfo

data class ProfileViewState(
    val userInfo: UserInfo
)

sealed class ProfileEvent {
    object LoadUserInfo : ProfileEvent()
    object Logout: ProfileEvent()
}

sealed class ProfileEffect : BaseEffect() {
    object OnLogout: ProfileEffect()
}
