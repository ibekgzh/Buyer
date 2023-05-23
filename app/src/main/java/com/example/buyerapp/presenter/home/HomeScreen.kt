package com.example.buyerapp.presenter.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.buyerapp.R
import com.example.buyerapp.core.navigation.NavigationProvider
import com.example.buyerapp.presenter.NavGraphs
import com.example.buyerapp.presenter.appCurrentDestinationAsState
import com.example.buyerapp.presenter.destinations.BarCodeScreenDestination
import com.example.buyerapp.presenter.destinations.MainScreenDestination
import com.example.buyerapp.presenter.destinations.ProfileScreenDestination
import com.example.buyerapp.presenter.startAppDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.example.buyerapp.presenter.destinations.Destination as HomeDestination

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HomeScreen(navigator: NavigationProvider) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = { MBottomNavigation(navController) }) {
        NavigationGraph(
            navController = navController,
            navigator = navigator,
            innerPadding = it
        )
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    navigator: NavigationProvider,
    innerPadding: PaddingValues
) {
    DestinationsNavHost(
        navController = navController,
        navGraph = NavGraphs.home,
        dependenciesContainerBuilder = {
            dependency(navigator)
        }
    )
}

@Composable
fun MBottomNavigation(navController: NavController) {

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val currentDestination: HomeDestination? =
            navController.appCurrentDestinationAsState().value
                ?: NavGraphs.home.startAppDestination

        BottomBarDestination.values().forEach { destination ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = destination.icon),
                        contentDescription = destination.label
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigate(destination.direction) {
                        popUpTo(navController.graph.id)
                    }
                }
            )
        }
    }
}

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: Int,
    val label: String
) {
    Main(MainScreenDestination, R.drawable.home_bottom_nav, "main"),
    Barcode(BarCodeScreenDestination, R.drawable.barcode_botton_nav, "bar_code"),
    Profile(ProfileScreenDestination, R.drawable.profile_bottom_nav, "profile"),
}