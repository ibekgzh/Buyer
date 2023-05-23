package com.example.buyerapp.presenter.home.tabs.profile

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.GetUserInfoUseCase
import com.example.buyerapp.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logoutUseCase: LogoutUseCase,
) : MviViewModel<ProfileViewState, ProfileEvent, ProfileEffect>() {

    override fun onTriggerEvent(eventType: ProfileEvent) {
        when (eventType) {
            is ProfileEvent.LoadUserInfo -> onLoadUserInfo()
            is ProfileEvent.Logout -> onLogout()
        }
    }

    private fun onLoadUserInfo() = safeLaunch {
        execute(getUserInfoUseCase(Unit)) {
            setState(ProfileViewState(userInfo = it))
        }
    }

    private fun onLogout() = safeLaunch {
        execute(logoutUseCase(Unit)){
            effectChannel.trySend(ProfileEffect.OnLogout)
        }
    }
}