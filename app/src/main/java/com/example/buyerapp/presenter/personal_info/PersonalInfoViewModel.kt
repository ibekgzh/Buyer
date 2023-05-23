package com.example.buyerapp.presenter.personal_info

import com.example.buyerapp.core.framework.mvi.MviViewModel
import com.example.buyerapp.domain.usecase.SaveUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonalInfoViewModel @Inject constructor(private val saveUserInfoUseCase: SaveUserInfoUseCase) :
    MviViewModel<PersonalInfoViewState, PersonalInfoEvent, PersonalInfoEffect>() {

    override fun onTriggerEvent(eventType: PersonalInfoEvent) {
        when (eventType) {
            is PersonalInfoEvent.SavePersonalInfo -> onSavePersonalInfo(
                eventType.fullName, eventType.cellPhone
            )
        }
    }

    private fun onSavePersonalInfo(fullName: String, cellPhone: String) = safeLaunch {
        val parts = fullName.split(" ");
        val lastname = parts[0].trim()
        val firstname = parts[1].trim()

        execute(saveUserInfoUseCase(SaveUserInfoUseCase.Params(firstname, lastname, cellPhone))) {
            effectChannel.trySend(PersonalInfoEffect.OnAfterSave)
        }
    }
}