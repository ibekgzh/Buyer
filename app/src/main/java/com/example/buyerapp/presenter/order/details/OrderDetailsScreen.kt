package com.example.buyerapp.presenter.order.details

import android.widget.Toast
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.order.details.view.OrderDetailsContent
import com.example.buyerapp.presenter.order.details.view.QrCodeContent
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun OrderDetailsScreen(
    id: Long,
    viewModel: OrderDetailsViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    val bottomSheetScaffoldState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(OrderDetailsEvent.LoadDetails(id))
    })

    val showHideBottomSheet: () -> Unit = {
        coroutineScope.launch {
            if (bottomSheetScaffoldState.isVisible) {
                bottomSheetScaffoldState.hide()
            } else {
                bottomSheetScaffoldState.show()
            }
        }
    }

    if (uiState.isLoading) {
        LoadingView()
    } else {
        uiState.state?.let { state ->
            SurfaceTopToolBarBack(
                onOnclickBackButton = { navigator.navigateUp() },
                title = "Чек"
            ) {
                ModalBottomSheetLayout(
                    sheetState = bottomSheetScaffoldState,
                    sheetContent = {
                        QrCodeContent(code = state.orderDetails.qrCode)
                    },
                    sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ) {
                    OrderDetailsContent(state.orderDetails) {
                        showHideBottomSheet()
                    }
                }
            }
        }
    }

    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                .show()
        }
    }
}