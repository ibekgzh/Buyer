package com.example.buyerapp.presenter.cart

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
import com.example.buyerapp.presenter.cart.view.CartScreenContent
import com.example.buyerapp.presenter.home.HomeTabsDestination
import com.example.buyerapp.presenter.order.details.OrderDetailType
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(CartEvent.LoadCartItems)
    })

    if (uiState.isLoading) {
        LoadingView()
    } else {
        uiState.state?.let {
            SurfaceTopToolBarBack(
                title = "Корзина",
                onOnclickBackButton = { navigator.navigateUp() }
            ) {
                CartScreenContent(
                    cart = it.cart,
                    onModify = { amount, itemId ->
                        viewModel.onTriggerEvent(CartEvent.Modify(amount, itemId))
                    },
                    onDelete = {
                        viewModel.onTriggerEvent(CartEvent.Delete(it.amount, it.item.id))
                    },
                    onOrder = {
                        viewModel.onTriggerEvent(CartEvent.Order(it.cart))
                    },
                    onAddMore = {
                        navigator.openHome(homeTabsDestination = HomeTabsDestination.Barcode)
                    }
                )
            }
        }
    }

    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                .show()

            is CartEffect.Ordered -> {
                Toast.makeText(
                    context,
                    "Оформлен заказ № ${effect.id}",
                    Toast.LENGTH_SHORT
                ).show()
                navigator.openOrderDetails(effect.id.toLong(), OrderDetailType.CART_SCREEN)
            }
        }
    }
}
