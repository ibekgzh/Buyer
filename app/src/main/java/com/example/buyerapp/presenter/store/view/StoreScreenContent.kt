package com.example.buyerapp.presenter.store.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.buyerapp.R
import com.example.buyerapp.domain.model.store.StoreDetails

@Composable
fun StoreScreenContent(
    storeDetails: StoreDetails,
    onChangedStore: () -> Unit,
    onClickBarCode: () -> Unit,
    onCheckedNotify: (checked: Boolean) -> Unit,
    imageLoader: ImageLoader
) {

    val largeLogo = rememberAsyncImagePainter(storeDetails.largeLogo, imageLoader)
    val logo = rememberAsyncImagePainter(storeDetails.logo, imageLoader)

    Surface(modifier = Modifier.fillMaxSize()) {
        Column {

            Boxes(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray)
                        .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                        .paint(largeLogo)
                )
                Box(
                    modifier = Modifier
                        .size(77.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .paint(logo)
                )
            }

            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                Text(
                    text = storeDetails.title.uppercase(),
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
                    text = storeDetails.descr,
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Button(
                        onClick = { onChangedStore() },
                        modifier = Modifier
                            .height(52.dp)
                            .weight(1.1f),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Black,
                            containerColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(80.dp)
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.refresh_circle),
                            contentDescription = "Bar code",
                            colorFilter = ColorFilter.tint(Color.Black),
                            modifier = Modifier.padding(end = 14.dp)
                        )

                        Text(
                            "Сменить магазин",
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W600,
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                        )
                    }

                    Spacer(modifier = Modifier.weight(0.1f))

                    Button(
                        onClick = { onClickBarCode() },
                        modifier = Modifier
                            .height(52.dp)
                            .weight(0.9f),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Black,
                            containerColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(80.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.barcode_botton_nav),
                            contentDescription = "Bar code",
                            colorFilter = ColorFilter.tint(Color.Black),
                            modifier = Modifier.padding(end = 14.dp)
                        )

                        Text(
                            "Bar code",
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W600,
                            fontSize = 12.sp,
                            lineHeight = 30.sp,
                        )
                    }
                }
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
                        modifier = Modifier
                            .scale(1.3f),
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = colorResource(id = R.color.switch_background),
                            checkedTrackColor = colorResource(id = R.color.switch_background),
                            uncheckedThumbColor = colorResource(id = R.color.gray4),
                            uncheckedTrackColor = colorResource(id = R.color.gray4)
                        ),
                        checked = storeDetails.notify,
                        onCheckedChange = {
                            onCheckedNotify(it)
                        }
                    )

                }
            }

        }
    }
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