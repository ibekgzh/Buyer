package com.example.buyerapp.presenter.pincode

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.core.widget.PinCodeView
import com.example.buyerapp.domain.model.longname
import com.example.buyerapp.presenter.confirm_otp.ConfirmOtpType
import com.example.buyerapp.presenter.destinations.HomeScreenDestination
import com.example.buyerapp.presenter.pincode.view.PinCodeHeader
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun PinCodeScreen(
    mode: PinCodeScreenMode,
    oldPin: String?,
    viewModel: PinCodeViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(PinCodeEvent.GetUserInfo)
    })

    val title: (mode: PinCodeScreenMode) -> String = {
        when (it) {
            PinCodeScreenMode.LOGIN -> "Введите текущий код-пароль для входа"
            PinCodeScreenMode.PASSWORD_SAFETY ->
                oldPin?.let { "Введите новый код-пароль для входа" }
                    ?: "Введите текущий код-пароль для входа"

        }
    }

    val onInputComplete: (pinCode: String) -> Unit = { pinCode ->
        when (mode) {
            PinCodeScreenMode.LOGIN ->
                viewModel.onTriggerEvent(PinCodeEvent.PinCheck(pinCode))

            PinCodeScreenMode.PASSWORD_SAFETY ->
                oldPin?.let {
                    viewModel.onTriggerEvent(
                        PinCodeEvent.PinChange(
                            oldPin,
                            pinCode
                        )
                    )
                } ?: navigator.openPinCode(
                    PinCodeScreenMode.PASSWORD_SAFETY,
                    pinCode
                )
        }
    }

    if (uiState.isLoading) {
        LoadingView()
    } else {
        uiState.state?.let {
            PinCodeBody(
                headerContent = {
                    PinCodeHeader(
                        fullName = it.userInfo.longname(),
                        onLogout = {
                            viewModel.onTriggerEvent(PinCodeEvent.Logout)
                        },
                        onClose = {
                            navigator.navigateUp()
                        }
                    )
                }) {

                PinCodeView(
                    length = 4,
                    title = title(mode),
                    onInputComplete = { onInputComplete(it) },
                    onForgetPinClick = {
                        navigator.openConfirmOtp(it.userInfo.cellphone, ConfirmOtpType.PIN_RESET)
                    }
                )
            }
        }
    }

    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                .show()

            is PinCodeEffect.OnNavigateHome -> navigator.openHome()
            is PinCodeEffect.OnNavigateUp -> navigator.navigateUp()
            is PinCodeEffect.OnNavigateUpProfile -> {
                Toast.makeText(context, "Код-пароль успешно изменен", Toast.LENGTH_SHORT).show()
                navigator.navigateUp(HomeScreenDestination)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PinCodeBody(
    headerContent: @Composable () -> Unit,
    pageContent: @Composable () -> Unit
) {
    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        ),
        sheetContent = { pageContent() },
        sheetPeekHeight = 500.dp,
    ) {
        Box {
            headerContent()
        }
    }
}

enum class PinCodeScreenMode {
    LOGIN,
    PASSWORD_SAFETY
}