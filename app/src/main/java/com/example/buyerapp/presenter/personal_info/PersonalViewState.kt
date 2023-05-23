package com.example.buyerapp.presenter.personal_info

import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.domain.model.UserInfo

data class PersonalInfoViewState(
    val userInfo: UserInfo
)

sealed class PersonalInfoEvent {
    data class SavePersonalInfo(val fullName: String, val cellPhone: String) : PersonalInfoEvent()
}

sealed class PersonalInfoEffect : BaseEffect() {
    object OnAfterSave : PersonalInfoEffect()
}