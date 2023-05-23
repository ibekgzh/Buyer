package com.example.buyerapp.presenter.pincode

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.navigation.NavigationProvider
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.core.widget.PinCodeView
import com.example.buyerapp.domain.model.longname
import com.example.buyerapp.presenter.pincode.view.PinCodeHeader
import com.example.buyerapp.presenter.pincode.PinCodeViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
fun PinCodeScreen(
    changePin: Boolean = false,
    authKey: String?,
    cellPhone: String?,
    navigator: NavigationProvider
) {

//    val uiState by viewModel.uiState.collectAsState()
//    val context = LocalContext.current
//
//    val changePinState = rememberSaveable { mutableStateOf(ChangePinState.OLD) }
//    val oldPin = rememberSaveable { mutableStateOf("") }
//
//    LaunchedEffect(key1 = viewModel) {
//        authKey?.let {
//            viewModel.onTriggerEvent(PinCodeEvent.LoadUserInfo)
//        }
//    }

//    PinCodeBody(
//        headerContent = {
//            PinCodeHeader(
//                fullName = uiState.state?.userInfo?.longname(),
//                onLogout = ({ viewModel.onTriggerEvent(PinCodeEvent.Logout) }),
//                onClose = { navigator.navigateUp() }
//            )
//        }
//    ) {
//        if (uiState.isLoading) {
//            LoadingView()
//        } else {
//            PinCodeView(
//                length = 4,
//                onInputComplete = { pin ->
//                    if (changePin) {
//                        if (changePinState.value == ChangePinState.OLD) {
//                            oldPin.value = pin
//                            changePinState.value = ChangePinState.NEW
//                        } else {
//                            viewModel.onTriggerEvent(PinCodeEvent.PinChange(oldPin.value, pin))
//                        }
//                    } else {
//                        authKey?.let {
//                            viewModel.onTriggerEvent(PinCodeEvent.PinCheck(pin))
//                        } ?: run {
//                            viewModel.onTriggerEvent(PinCodeEvent.Authorization(cellPhone!!, pin))
//                        }
//                    }
//                })
//        }
//    }

//    viewModel.effect.collectInLaunchedEffect { effect ->
//        when (effect) {
//            is BaseEffect.OnError -> {
//                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
//                    .show()
//
//                if (changePin) {
//                    navigator.navigateUp()
//                }
//            }
//
//            is PinCodeEffect.OnNavigateNewPinCode -> navigator.openNewPinCode(effect.oldPin)
//            is PinCodeEffect.OnNavigateHome -> navigator.openHome()
//            is PinCodeEffect.OnLogout -> {
//                navigator.navigateUp()
//            }
//
//            is PinCodeEffect.OnReturnToProfile -> {
//                Toast.makeText(context, "Пин код успешно изменен", Toast.LENGTH_SHORT).show()
//                navigator.navigateUp()
//            }
//        }
//    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
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

enum class ChangePinState {
    OLD, NEW
}