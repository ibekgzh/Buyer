package com.example.buyerapp.presenter.home.tabs.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.application.navigation.graph.HomeNavGraph
import com.example.buyerapp.application.navigation.home.HomeNavigationProvider
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.presenter.home.HomeTabsDestination
import com.example.buyerapp.presenter.home.tabs.main.view.Features
import com.example.buyerapp.presenter.home.tabs.main.view.Header
import com.example.buyerapp.presenter.home.tabs.main.view.MainCard
import com.ramcosta.composedestinations.annotation.Destination

@HomeNavGraph(start = true)
@Destination(start = true)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navigator: NavigationProvider,
    homeNavigator: HomeNavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(MainEvent.GetCachedStore)
    })

    uiState.state?.store?.let { store ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)) {
                Header(
                    storeTitle = store.title,
                    onClickCart = {
                        navigator.openCart()
                    },
                    onClickChooseShop = {
                        viewModel.onTriggerEvent(MainEvent.SelectShop)
                    },
                    onClickNotify = {
                        navigator.openLastNotificationsOfStores()
                    }
                )
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    MainCard(
                        onClickBarCode = {
                            homeNavigator.openBarCode()
                        }
                    )
                    Features(
                        store = store,
                        onClickShop = { navigator.openStore(store.id) },
                        onClickPromos = { navigator.openPromos(store.id) },
                        onClickOrderHistory = {
                            navigator.openOrderHistory()
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

            is MainEffect.DeletedCachedShop -> navigator.openHome(HomeTabsDestination.Main)
        }
    }
}
