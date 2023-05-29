package com.example.buyerapp.presenter.order_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavigatorProvider
import com.example.buyerapp.core.widget.LoadingView
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun OrderHistoryScreen(
    viewModel: OrderHistoryViewModel = hiltViewModel(),
    navigator: NavigatorProvider
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(OrderHistoryEvent.LoadOrders())
    })

    if (uiState.isLoading) {
        LoadingView()
    } else {

    }

}

@Composable
fun OrderHistoryBody(sheetContent: @Composable () -> Unit, pageContent: @Composable () -> Unit) {

}