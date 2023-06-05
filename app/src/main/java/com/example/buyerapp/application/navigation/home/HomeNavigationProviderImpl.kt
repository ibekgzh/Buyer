package com.example.buyerapp.application.navigation.home

import androidx.navigation.NavController
import com.example.buyerapp.presenter.destinations.BarCodeScreenDestination
import com.ramcosta.composedestinations.navigation.navigate
import javax.inject.Inject

class HomeNavigationProviderImpl @Inject constructor(private val navController: NavController) :
    HomeNavigationProvider {
    override fun openBarCode() {
        navController.navigate(BarCodeScreenDestination) {
            popUpTo(navController.graph.id)
        }
    }
}