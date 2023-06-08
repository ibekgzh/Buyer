package com.example.buyerapp.presenter.home.tabs.main.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R

@Composable
fun Header(storeTitle: String, onClickCart: () -> Unit, onClickChooseShop: () -> Unit, onClickNotify: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = storeTitle,
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W800,
            fontSize = 32.sp,
            lineHeight = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(0.6f)
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Row(modifier = Modifier.weight(0.3f)) {
            Box(
                Modifier
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .clickable { onClickCart() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.basket),
                    contentDescription = "Basket"
                )
            }

            Box(
                Modifier
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .clickable { onClickNotify() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Notification"
                )
            }
        }
    }
    Box(
        Modifier
            .height(25.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable { onClickChooseShop() }) {
        Text(
            text = "Выберите магазин",
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = colorResource(id = R.color.gray4),
            textAlign = TextAlign.Start
        )
    }
}