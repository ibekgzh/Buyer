package com.example.buyerapp.presenter.pincode

import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.biometric.BiometricPrompt.ERROR_NEGATIVE_BUTTON
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
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


    val biometricPrompt = BiometricPrompt(
        context as FragmentActivity,
        object: AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                navigator.openHome()
            }
            override fun onAuthenticationFailed() {
                Toast.makeText(context, "Отпечаток пальца не распознан. Попробуйте еще раз!", Toast.LENGTH_SHORT).show();
            }
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                Toast.makeText(context, "Отпечаток пальца не распознан. Попробуйте еще раз!", Toast.LENGTH_SHORT).show();
            }
        }
    )


    val checkAndAuthenticate: () -> Unit = {
        val biometricManager = BiometricManager.from(context as FragmentActivity);
        when(biometricManager.canAuthenticate(BIOMETRIC_WEAK)){
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Toast.makeText(context, "Biometric Authentication currently unavailable", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Toast.makeText(context, "Your device doesn't support Biometric Authentication", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Toast.makeText(context, "Your device doesn't have any fingerprint enrolled", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_SUCCESS -> {
                val promptInfo: BiometricPrompt.PromptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Вход")
                    .setSubtitle("Через отпечаток пальца")
                    .setDescription("Пожалуйста, вставьте палец для разблокировки")
                    .setNegativeButtonText("Отмена")
                    .build()
                biometricPrompt.authenticate(promptInfo)
            }
        }
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(PinCodeEvent.GetUserInfo)
        checkAndAuthenticate()
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

            Surface(modifier = Modifier.systemBarsPadding()) {
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
                            navigator.openConfirmOtp(
                                it.userInfo.cellphone,
                                ConfirmOtpType.PIN_RESET
                            )
                        },
                        onBiometricPrompt = {
                            checkAndAuthenticate()
                        }
                    )
                }
            }
        }
    }

    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                .show()

            is PinCodeEffect.OnNavigateHome -> navigator.openHome()
            is PinCodeEffect.OnLogout -> navigator.logout()
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