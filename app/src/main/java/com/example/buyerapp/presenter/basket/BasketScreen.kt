package com.example.buyerapp.presenter.basket

import androidx.compose.runtime.Composable
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.basket.view.BasketScreenContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun BasketScreen(navigator: NavigationProvider) {

    SurfaceTopToolBarBack(
        title = "Корзина",
        onOnclickBackButton = { navigator.navigateUp() }
    ) {
        BasketScreenContent()
    }
}
