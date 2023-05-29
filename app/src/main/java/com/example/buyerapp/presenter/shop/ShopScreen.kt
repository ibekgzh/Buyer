package com.example.buyerapp.presenter.shop

import androidx.compose.runtime.Composable
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.shop.view.ShopScreenContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ShopScreen(navigator: NavigationProvider) {

    SurfaceTopToolBarBack(
        onOnclickBackButton = { navigator.navigateUp() }
    ) {
        ShopScreenContent()
    }
}