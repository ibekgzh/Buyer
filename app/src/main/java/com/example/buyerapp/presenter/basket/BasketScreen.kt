package com.example.buyerapp.presenter.basket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.basket.view.BasketScreenContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun BasketScreen(navigator: NavigationProvider) {

    SurfaceTopToolBarBack(
        title = "Корзина",
        bottomButtonContent = { BottomBar() },
        onOnclickBackButton = { navigator.navigateUp() }
    ) {
        BasketScreenContent()
    }
}

@Composable
fun BottomBar() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 20.dp, end = 20.dp)
    ) {

        Divider(
            color = colorResource(id = R.color.gray2),
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Итого:",
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                lineHeight = 30.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Text(
                text = "₸ " + 6000,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500,
                fontSize = 26.sp,
                lineHeight = 30.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(80.dp)
        ) {
            Text(
                "Купить",
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                lineHeight = 30.sp,
            )
        }
    }
}