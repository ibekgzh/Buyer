package com.example.buyerapp.presenter.home.tabs.bar_code

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.buyerapp.core.navigation.graph.HomeNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@HomeNavGraph
@Destination
@Composable
fun BarCodeScreen() {

    Text("Bar code")
}