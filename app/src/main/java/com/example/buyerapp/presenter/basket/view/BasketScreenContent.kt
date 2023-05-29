package com.example.buyerapp.presenter.basket.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.buyerapp.core.widget.rememberMutableStateListOf

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasketScreenContent() {

    val list = rememberMutableStateListOf(
        "One",
        "Two",
        "Three",
        "Four",
        "Five",
        "Six"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(items = list, key = { _, item -> item.hashCode() }) { _, item ->
                val state = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            list.remove(item)
                        }
                        true
                    }
                )
                SwipeToDismiss(
                    state = state, background = {
                        Box(
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                                .padding(bottom = 20.dp)
                                .clip(shape = RoundedCornerShape(20.dp))
                                .background(colorResource(id = R.color.basket_remove_background))
                        ) {
                            Icon(
                                painterResource(id = R.drawable.trash),
                                contentDescription = "Delete",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 15.dp),
                                tint = Color.White
                            )
                        }
                    },
                    dismissContent = {
                        Card(item = item)
                    },
                    directions = setOf(DismissDirection.EndToStart)
                )
            }
            item {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(80.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    onClick = {

                    }
                ) {
                    Text(
                        text = "Добавить еще",
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 30.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        BottomBar()
    }
}

@Composable
fun Card(item: String) {

    var count by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .border(1.dp, colorResource(id = R.color.gray5), shape = RoundedCornerShape(20.dp))
            .clip(shape = RoundedCornerShape(20.dp))
            .background(colorResource(id = R.color.gray2))
            .clickable { }
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Рубашка SKU2Q3432423 Qazaq REP $item",
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W700,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                color = Color.Black
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier
                        .width(150.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(80.dp))
                        .clip(shape = RoundedCornerShape(80.dp))
                        .background(Color.White),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Box(
                        Modifier
                            .size(50.dp)
                            .clip(shape = RoundedCornerShape(20.dp))
                            .clickable {
                                if (count != 0) {
                                    count -= 1
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.minus),
                            contentDescription = "Minus",
                            tint = Color.Black
                        )
                    }

                    Text(
                        text = count.toString(),
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        lineHeight = 30.sp,
                        color = Color.Black,
                    )

                    Box(
                        Modifier
                            .size(50.dp)
                            .clip(shape = RoundedCornerShape(20.dp))
                            .clickable
                            {
                                count += 1
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "Plus",
                            tint = Color.Black
                        )
                    }
                }

                Text(
                    text = "₸ 6000",
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    lineHeight = 30.sp,
                    color = Color.Black,
                )
            }
        }
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
            modifier = Modifier.padding(top=10.dp, bottom = 5.dp)
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