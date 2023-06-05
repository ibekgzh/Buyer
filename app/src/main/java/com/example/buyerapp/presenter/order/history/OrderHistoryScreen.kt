package com.example.buyerapp.presenter.order.history

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.R
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.order.history.view.BottomSheetFilter
import com.example.buyerapp.presenter.order.history.view.DateFilterListView
import com.example.buyerapp.presenter.order.history.view.OrderItem
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Destination
@Composable
fun OrderHistoryScreen(
    viewModel: OrderHistoryViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val bottomSheetScaffoldState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val selectedFilter = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(OrderHistoryEvent.LoadOrders())
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

    when (uiState.isLoading) {
        true -> LoadingView()
        false ->
            SurfaceTopToolBarBack(
                onOnclickBackButton = { navigator.navigateUp() },
                title = "Последние транзакции",
                onShowCommonButton = true,
                onClickCommonButton = {
                    showHideBottomSheet()
                }
            ) {
                uiState.state?.let { state ->
                    ModalBottomSheetLayout(
                        sheetState = bottomSheetScaffoldState,
                        sheetContent = {
                            BottomSheetFilter(
                                selectedFilter = selectedFilter.value,
                                onSelected = {
                                    selectedFilter.value = it.code
                                    viewModel.onTriggerEvent(OrderHistoryEvent.LoadOrders(
                                        startDate = it.startDate
                                    ))
                                    showHideBottomSheet()
                                }
                            )
                        },
                        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    ) {
                        LazyColumn {
                            state.orders.groupBy { it.regDate }.map { group ->
                                stickyHeader {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 20.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .width(100.dp)
                                                .height(35.dp)
                                                .clip(shape = RoundedCornerShape(20.dp))
                                                .background(Color.Black),
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = group.key, color = Color.White
                                            )
                                        }
                                    }
                                }

                                items(group.value) {
                                    OrderItem(order = it, imageLoader = viewModel.imageLoader,
                                        onSelectItem = {
                                            navigator.openOrderDetails(it.id)
                                        }
                                    )
                                }
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
