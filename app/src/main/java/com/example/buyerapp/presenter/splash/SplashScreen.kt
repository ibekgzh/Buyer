package com.example.buyerapp.presenter.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.buyerapp.R
import com.example.buyerapp.core.navigation.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import kotlinx.coroutines.delay

@RootNavGraph(start = true)
@Destination(start = true)
@Composable
fun SplashScreen(navigator: NavigationProvider) {

    LaunchedEffect(key1 = true) {
        delay(2000)
//        navigator.openHome()
        navigator.openOnBoarding()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.on_boarding_by),
            contentDescription = "BY"
        )

        CircularProgressIndicator(
            modifier = Modifier
                .size(53.dp)
                .padding(vertical = 279.dp),
            color = colorResource(id = R.color.progress_view),
            strokeWidth = 3.dp
        )

    }
}