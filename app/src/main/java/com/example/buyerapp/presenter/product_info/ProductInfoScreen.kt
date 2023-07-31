package com.example.buyerapp.presenter.product_info

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.product_info.view.ProductInfoScreenContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProductInfoScreen(
    barcode: String,
    viewModel: ProductInfoViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel) {
        viewModel.onTriggerEvent(ProductEvent.LoadProductInfo(barcode))
    }

    when (uiState.isLoading) {
        true -> LoadingView()
        false -> {
            uiState.state?.let { product ->
                SurfaceTopToolBarBack(
                    onOnclickBackButton = { navigator.navigateUp() }
                ) {
                    ProductInfoScreenContent(
                        product.productInfo,
                        onClickAdd = { amount ->
                            viewModel.onTriggerEvent(
                                ProductEvent.AddProduct(
                                    amount,
                                    product.productInfo.id.toString()
                                )
                            )
                            navigator.openCart()
                        }
                    )
                }
            }
        }
    }

    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}