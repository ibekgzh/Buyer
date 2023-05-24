package com.example.buyerapp.presenter.confirm_otp

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.confirm_otp.view.ConfirmOtpContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ConfirmOtpScreen(
    confirmOtpType: ConfirmOtpType = ConfirmOtpType.AUTH_COMPLETE,
    cellPhone: String,
    viewModel: ConfirmOtpViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val startConfirmOtp: (confirmOtpType: ConfirmOtpType) -> Unit = {
        when (confirmOtpType) {
            ConfirmOtpType.AUTH_COMPLETE ->
                viewModel.onTriggerEvent(ConfirmOtpEvent.AuthStart(cellPhone))

            ConfirmOtpType.PIN_RESET ->
                viewModel.onTriggerEvent(ConfirmOtpEvent.PinResetSms)
        }
    }

    LaunchedEffect(key1 = viewModel, block = {
        startConfirmOtp(confirmOtpType)
    })

    if (uiState.isLoading) {
        LoadingView()
    } else {
        SurfaceTopToolBarBack(
            onOnclickBackButton = { navigator.navigateUp() }
        ) {
            ConfirmOtpContent(
                cellPhone,
                onInputPinComplete = {
                    navigator.openNewPinCode(it, cellPhone, confirmOtpType)
                },
                onRetry = {
                    startConfirmOtp(confirmOtpType)
                }
            )
        }
    }

    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                .show()
        }
    }
}

enum class ConfirmOtpType {
    AUTH_COMPLETE,
    PIN_RESET
}