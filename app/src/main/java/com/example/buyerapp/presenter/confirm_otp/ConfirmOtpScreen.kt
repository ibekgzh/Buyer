package com.example.buyerapp.presenter.confirm_otp

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.buyerapp.core.navigation.NavigationProvider
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.confirm_otp.view.ConfirmOtpContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ConfirmOtpScreen(
    cellPhone: String,
    navigator: NavigationProvider
) {

//    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    SurfaceTopToolBarBack(
        onOnclickBackButton = { navigator.navigateUp() }
    ) {
//        if (uiState.isLoading) {
//            LoadingView()
//        } else {
        ConfirmOtpContent(
            cellPhone,
            onInputPinComplete = {
                navigator.openNewPinCode(it, cellPhone)
//                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//                    viewModel.onTriggerEvent(NewPinCodeEvent.PinChange(it, oldPin))
            }
        )
//        }
    }
}
