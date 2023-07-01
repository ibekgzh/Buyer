package com.example.buyerapp.presenter.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.buyerapp.R
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.application.navigation.home.HomeNavigationProviderImpl
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.presenter.NavGraphs
import com.example.buyerapp.presenter.appCurrentDestinationAsState
import com.example.buyerapp.presenter.destinations.BarCodeScreenDestination
import com.example.buyerapp.presenter.destinations.MainScreenDestination
import com.example.buyerapp.presenter.destinations.ProfileScreenDestination
import com.example.buyerapp.presenter.home.view.StoreFilter
import com.example.buyerapp.presenter.startAppDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.example.buyerapp.presenter.destinations.Destination as HomeDestination

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Destination
@Composable
fun HomeScreen(
    forFilterPromo: Boolean,
    homeDestination: HomeTabsDestination,
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val navController = rememberNavController()

    LaunchedEffect(key1 = viewModel, block = {
        if(forFilterPromo) {
            viewModel.onTriggerEvent(HomeEvent.SearchStore(true))
        }
        else {
            viewModel.onTriggerEvent(HomeEvent.GetCachedStore)
        }
    })

    if (uiState.isLoading) {
        LoadingView()
    } else {
        BottomSheetScaffold(
            sheetContent = {
                uiState.state?.stores?.let { pageStores ->
                    StoreFilter(pageStores, onSearchTextChanged = {
                        viewModel.onTriggerEvent(HomeEvent.SearchStore(searchWord = it))
                    }) { store ->
                        if (forFilterPromo) {
                            navigator.openPromos(store.id)
                        } else {
                            viewModel.onTriggerEvent(HomeEvent.ChooseStore(store))
                        }
                    }
                }
            },
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetPeekHeight = if (uiState.state?.hasChosenStore == true && !forFilterPromo) 0.dp else 700.dp

        ) {
            Surface(modifier = Modifier.systemBarsPadding()) {
                Scaffold(
                    bottomBar = { MBottomNavigation(navController) }) {
                    NavigationGraph(
                        homeDestination,
                        navController = navController,
                        navigator = navigator
                    )
                }
            }
        }
    }

    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                .show()

            is HomeEffect.ReOpenHome -> navigator.openHome(HomeTabsDestination.Main)
        }
    }
}

@Composable
fun NavigationGraph(
    homeTabsDestination: HomeTabsDestination,
    navController: NavHostController,
    navigator: NavigationProvider
) {
    DestinationsNavHost(
        startRoute = homeTabsDestination.direction,
        navController = navController,
        navGraph = NavGraphs.home,
        dependenciesContainerBuilder = {
            dependency(navigator)
            dependency(HomeNavigationProviderImpl(navController))
        }
    )
}

@Composable
fun MBottomNavigation(navController: NavController) {

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val currentDestination: HomeDestination =
            navController.appCurrentDestinationAsState().value
                ?: NavGraphs.home.startAppDestination

        HomeTabsDestination.values().forEach { destination ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = destination.icon),
                        contentDescription = destination.label
                    )
                },
                selectedContentColor = Color.Black.copy(1f),
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

enum class HomeTabsDestination(
    val direction: DirectionDestinationSpec,
    val icon: Int,
    val label: String
) {
    Main(MainScreenDestination, R.drawable.home_bottom_nav, "main"),
    Barcode(BarCodeScreenDestination, R.drawable.barcode_botton_nav, "bar_code"),
    Profile(ProfileScreenDestination, R.drawable.profile_bottom_nav, "profile"),
}