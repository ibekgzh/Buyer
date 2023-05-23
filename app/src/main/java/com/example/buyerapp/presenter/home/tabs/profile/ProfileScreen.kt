package com.example.buyerapp.presenter.home.tabs.profile

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.navigation.NavigationProvider
import com.example.buyerapp.core.navigation.graph.HomeNavGraph
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.presenter.home.tabs.profile.view.ProfileContent
import com.ramcosta.composedestinations.annotation.Destination

@HomeNavGraph
@Destination
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigator: NavigationProvider,
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(ProfileEvent.LoadUserInfo)
    })

    if (uiState.isLoading) {
        LoadingView()
    } else {
        uiState.state?.let {
            ProfileContent(
                data = it,
                onClickPersonalInfo = {
                    navigator.openPersonalInfo(
                        it.userInfo.firstname,
                        it.userInfo.lastname,
                        it.userInfo.cellphone
                    )
                },
                onClickPasswordSafety = { navigator.openPinCode(changePin = true) },
                onClickLogout = { viewModel.onTriggerEvent(ProfileEvent.Logout) }
            )
        }
    }

    viewModel.effect.collectInLaunchedEffect {
        when (it) {
            is BaseEffect.OnError -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }

            is ProfileEffect.OnLogout -> {
                navigator.navigateUp()
            }
        }
    }
}