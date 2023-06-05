package com.example.buyerapp.presenter.home.tabs.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.example.buyerapp.domain.model.Store

@Composable
fun Features(store: Store, onClickShop: () -> Unit, onClickPromos: () -> Unit, onClickOrderHistory: () -> Unit) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 50.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .size(160.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(colorResource(id = R.color.shop_background))
                    .clickable { onClickShop() }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .paint(
                            painterResource(id = R.drawable.shop),
                            alignment = Alignment.TopEnd
                        ),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = store.title,
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 22.dp, bottom = 20.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .size(160.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(colorResource(id = R.color.discount_background))
                    .clickable {
                        onClickPromos()
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .paint(
                            painterResource(id = R.drawable.discount),
                            alignment = Alignment.CenterEnd
                        ),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = "Скидки",
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 22.dp, bottom = 20.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 15.dp)
                .height(160.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))
                .background(colorResource(id = R.color.transaction_background))
                .clickable {
                    onClickOrderHistory()
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.transaction),
                        alignment = Alignment.CenterEnd
                    ),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Последние транзакции",
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W700,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 22.dp, bottom = 20.dp)
                )
            }
        }
    }
}