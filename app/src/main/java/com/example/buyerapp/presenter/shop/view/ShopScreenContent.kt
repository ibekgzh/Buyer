package com.example.buyerapp.presenter.shop.view

import android.media.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import java.util.Locale

@Composable
fun ShopScreenContent() {

    var switchOn by remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column {

            Boxes(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray)
                        .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                        .paint(painterResource(id = R.drawable.shop))
                )
                Box(
                    modifier = Modifier
                        .size(77.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .paint(painterResource(id = R.drawable.shop_logo))
                )
            }

            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                Text(
                    text = "Marvin".uppercase(),
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W800,
                    fontSize = 24.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 17.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. Exercitation veniam consequat sunt nostrud amet.",
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W300,
                    fontSize = 16.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 11.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Divider(
                    color = colorResource(id = R.color.gray2),
                    thickness = 1.dp,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, bottom = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Подписаться на уведомление",
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 30.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Switch(
                        checked = switchOn,
                        onCheckedChange = { switchOn_ ->
                            switchOn = switchOn_
                        }
                    )

                }
            }

        }
    }

//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Boxes {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//                    .background(Color.LightGray)
//                    .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
//                    .paint(painterResource(id = R.drawable.shop))
//            )
//            Box(
//                modifier = Modifier
//                    .size(77.dp)
//                    .clip(shape = RoundedCornerShape(20.dp))
//                    .paint(painterResource(id = R.drawable.shop_logo))
//            )
//        }
//
//        Box(
//            modifier = Modifier
//                .align(Alignment.Center)
//                .padding(20.dp)
//        ) {
//            Text(
//                text = "Marvin".uppercase(),
//                fontFamily = FontFamily.Default,
//                fontStyle = FontStyle.Normal,
//                fontWeight = FontWeight.W800,
//                fontSize = 24.sp,
//                lineHeight = 30.sp,
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//            )
//
//            Text(
//                text = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. Exercitation veniam consequat sunt nostrud amet.",
//                fontFamily = FontFamily.Default,
//                fontStyle = FontStyle.Normal,
//                fontWeight = FontWeight.W300,
//                fontSize = 16.sp,
//                lineHeight = 30.sp,
//                modifier = Modifier
//                    .align(Alignment.Center)
//            )
//
//        }
//
//        Box(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(start = 20.dp, end = 20.dp, top = 40.dp, bottom = 40.dp)
//        ) {
//            Divider(
//                color = colorResource(id = R.color.gray2),
//                thickness = 1.dp,
//            )
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "Подписаться на уведомление",
//                    fontFamily = FontFamily.Default,
//                    fontStyle = FontStyle.Normal,
//                    fontWeight = FontWeight.W600,
//                    fontSize = 16.sp,
//                    lineHeight = 30.sp,
//                    color = Color.Black,
//                    textAlign = TextAlign.Center
//                )
//
//                Switch(
//                    checked = switchOn,
//                    onCheckedChange = { switchOn_ ->
//                        switchOn = switchOn_
//                    }
//                )
//
//            }
//        }
//
//    }
}

@Composable
fun Boxes(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val largeBox = measurables[0]
        val smallBox = measurables[1]
        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )
        val largePlaceable = largeBox.measure(looseConstraints)
        val smallPlaceable = smallBox.measure(looseConstraints)
        layout(
            width = constraints.maxWidth,
            height = largePlaceable.height + smallPlaceable.height / 2,
        ) {
            largePlaceable.placeRelative(
                x = 0,
                y = 0,
            )
            smallPlaceable.placeRelative(
                x = (constraints.maxWidth - smallPlaceable.width) / 2,
                y = largePlaceable.height - smallPlaceable.height / 2
            )
        }
    }
}