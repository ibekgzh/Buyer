package com.example.buyerapp.presenter.order_history

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.presenter.order_history.view.DateFilterListView
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun OrderHistoryScreen(
    viewModel: OrderHistoryViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(OrderHistoryEvent.LoadOrders())
    })

    if (uiState.isLoading) {
        LoadingView()
    } else {
        uiState.state?.let { state ->
            BottomSheetScaffold(
                sheetContent = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(Color.White)
                    ) {
                        Text("Фильтр по дате")
                        DateFilterListView(selected = 0, onSelected = {

                        })
                        Button(onClick = {}) {
                            Text(text = "Применить")
                        }
                    }
                },
                scaffoldState = bottomSheetState,
                drawerGesturesEnabled = true
            ) {
                Box {
                    LazyRow {
                        state.orders.map {

                        }
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
