package com.example.buyerapp.presenter.personal_info

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.navigation.NavigationProvider
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.personal_info.view.PersonalInfoContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun PersonalInfoScreen(
    firstname: String,
    lastname: String,
    cellphone: String,
    viewModel: PersonalInfoViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    when (uiState.isLoading) {
        true -> LoadingView()
        false ->
            SurfaceTopToolBarBack(
                onOnclickBackButton = { navigator.navigateUp() }
            ) {
                PersonalInfoContent(
                    firstname,
                    lastname,
                    cellphone,
                    onClickSave = {
                        viewModel.onTriggerEvent(PersonalInfoEvent.SavePersonalInfo(it, cellphone))
                    }
                )
            }
    }

    viewModel.effect.collectInLaunchedEffect {
        when (it) {
            is BaseEffect.OnError -> {
                Toast.makeText(context, "Unknown Error", Toast.LENGTH_SHORT).show()
            }

            is PersonalInfoEffect.OnAfterSave -> {
                Toast.makeText(context, "Данные по клиенту успешно сохранены", Toast.LENGTH_SHORT)
                    .show()
                navigator.navigateUp()
            }
        }
    }
}