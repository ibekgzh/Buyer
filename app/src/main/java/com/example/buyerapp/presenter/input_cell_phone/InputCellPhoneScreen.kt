package com.example.buyerapp.presenter.input_cell_phone

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.R
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.navigation.NavigationProvider
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.input_cell_phone.view.InputCellPhoneContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun InputCellPhoneScreen(
    viewModel: InputCellPhoneViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    SurfaceTopToolBarBack(
        onOnclickBackButton = { navigator.navigateUp() }
    ) {
        if (uiState.isLoading) {
            LoadingView()
        } else {
            InputCellPhoneContent(
                onClickNext = {
                    if (it.length == 11) {
                        viewModel.onTriggerEvent(InputCellPhoneEvent.OnSendPhoneToReceiveOtp(it))
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.input_correct_format_phone),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
    }

    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                .show()

            is InputCellPhoneEffect.OnNavigateConfirmCellPhone -> navigator.openConfirmOtp(cellphone = effect.cellphone)
        }
    }
}